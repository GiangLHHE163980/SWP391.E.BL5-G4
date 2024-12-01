CREATE DATABASE SWP391_G5_V4;
GO
USE SWP391_G5_V4;
GO

--Lưu thông tin cơ bản của người dùng.
CREATE TABLE [dbo].[Users](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](100) NOT NULL,
	[FullName] [nvarchar](255) NOT NULL,
	[Email] [nvarchar](255) NOT NULL,
	[PasswordHash] [nvarchar](500) NOT NULL,
	[PhoneNumber] [nvarchar](20) NULL,
	[Address] [nvarchar](500) NULL,
	[Avatar] [nvarchar](255) NULL,
	[IsActive] [bit] NULL,
	[CreatedAt] [datetime] NULL,
	[UpdatedAt] [datetime] NULL,
	[SocialSecurityNumber] [int] NOT NULL,
	[Sex] [nvarchar](10) NOT NULL,
	[Birthday] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Users] ADD  DEFAULT ((1)) FOR [IsActive]
GO

ALTER TABLE [dbo].[Users] ADD  DEFAULT (getdate()) FOR [CreatedAt]
GO

ALTER TABLE [dbo].[Users] ADD  DEFAULT ((0)) FOR [SocialSecurityNumber]
GO

ALTER TABLE [dbo].[Users] ADD  DEFAULT ('Unknown') FOR [Sex]
GO

--Quản lý các vai trò trong hệ thống.
CREATE TABLE Roles (
    RoleID INT PRIMARY KEY IDENTITY(1,1),
    RoleName NVARCHAR(50) UNIQUE NOT NULL -- Ví dụ: Admin, Customer, Staff
);

--Bảng trung gian giữa Users và Roles.
CREATE TABLE UserRoles (
    UserRoleID INT PRIMARY KEY IDENTITY(1,1),
    UserID INT NOT NULL,
    RoleID INT NOT NULL,
    AssignedAt DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);

-- Nhà cung cấp bảo hiểm
CREATE TABLE InsuranceCompanies (
    CompanyID INT PRIMARY KEY IDENTITY(1,1),
    CompanyName NVARCHAR(255) NOT NULL, -- Tên công ty bảo hiểm
    Address NVARCHAR(255), -- Địa chỉ công ty
    ContactInfo NVARCHAR(255) -- Thông tin liên hệ
);


-- Lưu thông tin các sản phẩm bảo hiểm
CREATE TABLE InsuranceProducts (
    ProductID INT PRIMARY KEY IDENTITY(1,1),
    CompanyID INT, -- Nhà cung cấp
    ProductName NVARCHAR(255) NOT NULL, -- Tên sản phẩm bảo hiểm
    InsuranceType NVARCHAR(50) NOT NULL, -- Loại bảo hiểm (Ví dụ: Y tế, Xe, Nhân thọ)
    Description NVARCHAR(MAX), -- Mô tả chi tiết sản phẩm
    Cost DECIMAL(18,2) NOT NULL, -- Chi phí bảo hiểm
    Conditions NVARCHAR(MAX), -- Điều kiện bảo hiểm
    CreatedAt DATETIME DEFAULT GETDATE(),
    UpdatedAt DATETIME NULL,
    CONSTRAINT FK_InsuranceProducts_InsuranceCompanies FOREIGN KEY (CompanyID) REFERENCES InsuranceCompanies(CompanyID)
);

--Lưu thông tin thẻ bảo hiểm của khách hàng
CREATE TABLE InsuranceCards (
    CardID INT PRIMARY KEY IDENTITY(1,1),
    CardNumber NVARCHAR(50) UNIQUE NOT NULL, -- Số thẻ bảo hiểm
    UserID INT NOT NULL, -- Thuộc khách hàng nào
    ProductID INT NOT NULL, -- Loại sản phẩm bảo hiểm
    StartDate DATE NOT NULL, -- Ngày bắt đầu bảo hiểm
    EndDate DATE NOT NULL, -- Ngày hết hạn bảo hiểm
    Status NVARCHAR(50) DEFAULT 'Active', -- Trạng thái: Active, Expired, Revoked
    CreatedAt DATETIME DEFAULT GETDATE(),
    UpdatedAt DATETIME NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ProductID) REFERENCES InsuranceProducts(ProductID)
);

--Lưu thông tin yêu cầu bảo hiểm của khách hàng.
CREATE TABLE Claims (
    ClaimID INT PRIMARY KEY IDENTITY(1,1),
    CardID INT NOT NULL, -- Thuộc thẻ bảo hiểm nào
    UserID INT NOT NULL, -- Người gửi yêu cầu
    ClaimType NVARCHAR(50), -- Loại yêu cầu: Y tế, tai nạn, xe cộ, v.v.
    Status NVARCHAR(50) DEFAULT 'Pending', -- Trạng thái: Pending, Approved, Rejected
    Reason NVARCHAR(MAX) NULL, -- Lý do phê duyệt hoặc từ chối
    SubmittedAt DATETIME DEFAULT GETDATE(),
    ProcessedAt DATETIME NULL,
    FOREIGN KEY (CardID) REFERENCES InsuranceCards(CardID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

--Lưu tài liệu liên quan đến yêu cầu bảo hiểm hoặc hồ sơ cá nhân.
CREATE TABLE Documents (
    DocumentID INT PRIMARY KEY IDENTITY(1,1),
    ClaimID INT NULL, -- Liên kết với yêu cầu bảo hiểm (nếu có)
    UserID INT NOT NULL, -- Người tải tài liệu
    DocumentName NVARCHAR(255), -- Tên tài liệu
    FilePath NVARCHAR(500) NOT NULL, -- Đường dẫn lưu trữ tài liệu
    UploadedAt DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (ClaimID) REFERENCES Claims(ClaimID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

--Lưu thông báo gửi đến người dùng.
CREATE TABLE Notifications (
    NotificationID INT PRIMARY KEY IDENTITY(1,1),
    UserID INT NOT NULL, -- Người nhận thông báo
    Title NVARCHAR(255) NOT NULL, -- Tiêu đề thông báo
    Message NVARCHAR(MAX), -- Nội dung thông báo
    IsRead BIT DEFAULT 0, -- Trạng thái đọc
    CreatedAt DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

--Lưu lịch sử hoạt động của người dùng trong hệ thống (hữu ích cho Admin).
CREATE TABLE AuditLogs (
    LogID INT PRIMARY KEY IDENTITY(1,1),
    UserID INT NOT NULL, -- Ai thực hiện hành động
    Action NVARCHAR(MAX) NOT NULL, -- Nội dung hành động
    CreatedAt DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);
-------------------------Add data-----------------------------------


-- Thêm dữ liệu vào bảng Roles
INSERT INTO Roles (RoleName)
VALUES 
    ('Admin'),
    ('Customer'),
    ('Staff');

-- Thêm dữ liệu vào bảng Users
INSERT INTO [dbo].[Users] (Username, FullName, Email, PasswordHash, PhoneNumber, Address, Avatar, IsActive, SocialSecurityNumber, Sex, Birthday)
VALUES
('john_doe', 'John Doe', 'john.doe@example.com', 'hashed_password_123', '123456789', '123 Main St, City', 'john_avatar.png', 1, 123456789, 'Male', '1990-01-01'),
('jane_smith', 'Jane Smith', 'jane.smith@example.com', 'hashed_password_456', '987654321', '456 Oak St, City', 'jane_avatar.png', 1, 987654321, 'Female', '1995-02-15'),
('alice_wong', 'Alice Wong', 'alice.wong@example.com', 'hashed_password_789', '5647382910', '789 Pine St, City', 'alice_avatar.png', 1, 223344556, 'Female', '1987-03-22'),
('bob_johnson', 'Bob Johnson', 'bob.johnson@example.com', 'hashed_password_012', '6789451230', '101 Maple St, City', 'bob_avatar.png', 1, 334455667, 'Male', '1992-07-10'),
('emma_taylor', 'Emma Taylor', 'emma.taylor@example.com', 'hashed_password_345', '9081726354', '202 Birch St, City', 'emma_avatar.png', 1, 445566778, 'Female', '1998-11-03');




-- Gán vai trò cho người dùng
INSERT INTO UserRoles (UserID, RoleID, AssignedAt)
VALUES
(1, 1, GETDATE()),
(2, 2, GETDATE()),
(3, 2, GETDATE()),
(4, 3, GETDATE()),
(5, 3, GETDATE());



-- Thêm dữ liệu vào bảng InsuranceCompanies
INSERT INTO InsuranceCompanies (CompanyName, Address, ContactInfo)
VALUES
('Company A', '123 Elm St, City', 'contact@companya.com'),
('Company B', '456 Oak St, City', 'contact@companyb.com'),
('Company C', '789 Maple St, City', 'contact@companyc.com'),
('Company D', '101 Pine St, City', 'contact@companyd.com'),
('Company E', '202 Birch St, City', 'contact@companye.com');






-- Thêm dữ liệu mẫu vào bảng InsuranceProducts
INSERT INTO InsuranceProducts (CompanyID, ProductName, InsuranceType, Description, Cost, Conditions)
VALUES
(1, 'Health Plan A', 'Health', 'Comprehensive health insurance', 500.00, 'No pre-existing conditions'),
(2, 'Car Plan B', 'Auto', 'Full coverage for vehicles', 300.00, 'Must have a valid driver’s license'),
(3, 'Life Plan C', 'Life', 'Term life insurance', 700.00, 'Minimum age 18'),
(4, 'Travel Plan D', 'Travel', 'Worldwide travel insurance', 150.00, 'No recent travel restrictions'),
(5, 'Home Plan E', 'Home', 'Homeowners insurance', 450.00, 'Must own property');



-- Thêm dữ liệu vào bảng InsuranceCards
INSERT INTO InsuranceCards (CardNumber, UserID, ProductID, StartDate, EndDate, Status)
VALUES
('CARD123', 1, 1, '2023-01-01', '2024-01-01', 'Active'),
('CARD456', 2, 2, '2023-02-01', '2024-02-01', 'Active'),
('CARD789', 3, 3, '2023-03-01', '2024-03-01', 'Active'),
('CARD012', 4, 4, '2023-04-01', '2024-04-01', 'Active'),
('CARD345', 5, 5, '2023-05-01', '2024-05-01', 'Expired');





-- Thêm dữ liệu vào bảng Claims
INSERT INTO Claims (CardID, UserID, ClaimType, Status, Reason, SubmittedAt)
VALUES
(1, 1, 'Health', 'Pending', 'Routine check-up', GETDATE()),
(2, 2, 'Auto', 'Approved', 'Car accident', GETDATE()),
(3, 3, 'Life', 'Rejected', 'Claim not eligible', GETDATE()),
(4, 4, 'Travel', 'Pending', 'Flight cancellation', GETDATE()),
(5, 5, 'Home', 'Approved', 'Flood damage', GETDATE());





-- Thêm dữ liệu vào bảng Documents
-- Giả sử ClaimID = 1 (Yêu cầu bảo hiểm của John Doe) và UserID = 1
INSERT INTO Documents (ClaimID, UserID, DocumentName, FilePath, UploadedAt)
VALUES
(1, 1, 'Health Report', 'files/health_report_1.pdf', GETDATE()),
(2, 2, 'Accident Report', 'files/accident_report_2.pdf', GETDATE()),
(3, 3, 'Life Insurance Document', 'files/life_doc_3.pdf', GETDATE()),
(4, 4, 'Travel Insurance Receipt', 'files/travel_receipt_4.pdf', GETDATE()),
(5, 5, 'Home Repair Invoice', 'files/home_invoice_5.pdf', GETDATE());





-- Thêm dữ liệu vào bảng Notifications
INSERT INTO Notifications (UserID, Title, Message, IsRead, CreatedAt)
VALUES
(1, 'Welcome', 'Welcome to our platform!', 0, GETDATE()),
(2, 'Claim Approved', 'Your claim has been approved.', 0, GETDATE()),
(3, 'Claim Rejected', 'Your claim has been rejected.', 0, GETDATE()),
(4, 'Policy Update', 'Your insurance policy has been updated.', 0, GETDATE()),
(5, 'Renewal Reminder', 'Your insurance policy is due for renewal.', 0, GETDATE());





-- Thêm dữ liệu vào bảng AuditLogs
INSERT INTO AuditLogs (UserID, Action, CreatedAt)
VALUES
(1, 'Logged in', GETDATE()),
(2, 'Submitted a claim', GETDATE()),
(3, 'Updated profile', GETDATE()),
(4, 'Made a payment', GETDATE()),
(5, 'Logged out', GETDATE());



