CREATE DATABASE SWP391_G5_V3;
GO
USE SWP391_G5_V3;
GO

--Lưu thông tin cơ bản của người dùng.
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY(1,1),
    FullName NVARCHAR(255) NOT NULL,
    Email NVARCHAR(255) UNIQUE NOT NULL,
    PasswordHash NVARCHAR(500) NOT NULL,
    PhoneNumber NVARCHAR(20),
    Address NVARCHAR(500),
    Avatar NVARCHAR(255), -- Ảnh đại diện (nếu có)
    IsActive BIT DEFAULT 1, -- Trạng thái tài khoản: 1 = hoạt động, 0 = khóa
    CreatedAt DATETIME DEFAULT GETDATE(),
    UpdatedAt DATETIME NULL
);

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


--Lưu thông tin các sản phẩm bảo hiểm.
CREATE TABLE InsuranceProducts (
    ProductID INT PRIMARY KEY IDENTITY(1,1),
	CompanyID INT, -- Nhà cung cấp
    ProductName NVARCHAR(255) NOT NULL, -- Tên sản phẩm bảo hiểm
    Description NVARCHAR(MAX), -- Mô tả chi tiết sản phẩm
    Cost DECIMAL(18,2) NOT NULL, -- Chi phí bảo hiểm
    Conditions NVARCHAR(MAX), -- Điều kiện bảo hiểm
    CreatedAt DATETIME DEFAULT GETDATE(),
    UpdatedAt DATETIME NULL
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
INSERT INTO Users (FullName, Email, PasswordHash, PhoneNumber, Address, Avatar)
VALUES 
    ('Nguyen Van A', 'nguyenvana@example.com', 'hashedpassword1', '0901234567', '123 ABC Street, Hanoi', 'avatar1.jpg'),
    ('Tran Thi B', 'tranthib@example.com', 'hashedpassword2', '0909876543', '456 DEF Street, Hanoi', 'avatar2.jpg');

-- Gán vai trò cho người dùng
INSERT INTO UserRoles (UserID, RoleID)
VALUES 
    (1, 1), -- Nguyen Van A được gán vai trò Admin
    (2, 2); -- Tran Thi B được gán vai trò Customer

-- Thêm dữ liệu vào bảng InsuranceCompanies
INSERT INTO InsuranceCompanies (CompanyName, Address, ContactInfo)
VALUES 
    ('ABC Insurance', '123 Main St, Hanoi', 'contact@abcinsurance.com'),
    ('XYZ Insurance', '456 Another St, Hanoi', 'contact@xyzinsurance.com');

-- Thêm dữ liệu vào bảng InsuranceProducts
INSERT INTO InsuranceProducts (CompanyID, ProductName, Description, Cost, Conditions)
VALUES 
    (1, 'Health Insurance', 'Comprehensive health coverage including hospital stays and surgeries', 500.00, 'No pre-existing conditions'),
    (2, 'Car Insurance', 'Covers damages to the car due to accidents or theft', 200.00, 'Must have a valid driver');

-- Thêm dữ liệu vào bảng InsuranceCards
INSERT INTO InsuranceCards (CardNumber, UserID, ProductID, StartDate, EndDate)
VALUES 
    ('IC123456789', 1, 1, '2024-01-01', '2025-01-01'),
    ('IC987654321', 2, 2, '2024-02-01', '2025-02-01');

-- Thêm dữ liệu vào bảng Claims
INSERT INTO Claims (CardID, UserID, ClaimType, Status, Reason)
VALUES 
    (1, 1, 'Medical', 'Pending', 'Request for hospitalization coverage'),
    (2, 2, 'Accident', 'Approved', 'Car accident coverage approved');

-- Thêm dữ liệu vào bảng Documents
INSERT INTO Documents (ClaimID, UserID, DocumentName, FilePath)
VALUES 
    (1, 1, 'HospitalBill.pdf', 'C:/Documents/HospitalBill.pdf'),
    (2, 2, 'CarDamageReport.pdf', 'C:/Documents/CarDamageReport.pdf');

-- Thêm dữ liệu vào bảng Notifications
INSERT INTO Notifications (UserID, Title, Message)
VALUES 
    (1, 'Claim Processed', 'Your medical claim has been approved.'),
    (2, 'Claim Status', 'Your car accident claim is being processed.');

-- Thêm dữ liệu vào bảng AuditLogs
INSERT INTO AuditLogs (UserID, Action)
VALUES 
    (1, 'Created a new insurance product for Health Insurance'),
    (2, 'Submitted a claim for car accident coverage');
