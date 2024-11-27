CREATE DATABASE SWP391_G5_V2;
GO
USE SWP391_G5_V2;
GO

-- Roles Table
CREATE TABLE Roles (
    RoleID INT PRIMARY KEY IDENTITY,
    RoleName NVARCHAR(50) NOT NULL UNIQUE -- 'Customer', 'Staff', 'Admin'
);

-- Users Table
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY,
    Username NVARCHAR(255) NOT NULL UNIQUE,
    PasswordHash NVARCHAR(255) NOT NULL,
    Email NVARCHAR(255) NOT NULL UNIQUE,
    RoleID INT NOT NULL FOREIGN KEY REFERENCES Roles(RoleID),
    FullName NVARCHAR(255),
    PhoneNumber NVARCHAR(20),
    Address NVARCHAR(255),
    CreatedAt DATETIME DEFAULT GETDATE(),
    LastLogin DATETIME
);

-- Customers Table
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY IDENTITY,
    UserID INT NOT NULL FOREIGN KEY REFERENCES Users(UserID) ON DELETE CASCADE,
    MembershipDate DATETIME DEFAULT GETDATE(),
    Status NVARCHAR(50) DEFAULT 'Active' -- 'Active', 'Inactive'
);

-- Staff Table
CREATE TABLE Staff (
    StaffID INT PRIMARY KEY IDENTITY,
    UserID INT NOT NULL FOREIGN KEY REFERENCES Users(UserID) ON DELETE CASCADE,
    Department NVARCHAR(255),
    Position NVARCHAR(255),
    StartDate DATE
);

-- Contracts Table
CREATE TABLE Contracts (
    ContractID INT PRIMARY KEY IDENTITY,
    CustomerID INT NOT NULL FOREIGN KEY REFERENCES Customers(CustomerID) ON DELETE CASCADE,
    ContractType NVARCHAR(255),
    StartDate DATETIME NOT NULL,
    EndDate DATETIME NOT NULL,
    Status NVARCHAR(50), -- 'Active', 'Expired', 'Cancelled', 'Renewed'
    ContractDetails NVARCHAR(MAX),
    CreatedAt DATETIME DEFAULT GETDATE(),
    StaffID INT NOT NULL,  -- Cột mới liên kết nhân viên với hợp đồng
    CONSTRAINT FK_Contracts_Staff FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)  -- Khóa ngoại liên kết với bảng Staff
);

-- Payment History Table
CREATE TABLE PaymentHistory (
    PaymentID INT PRIMARY KEY IDENTITY,
    CustomerID INT NOT NULL FOREIGN KEY REFERENCES Customers(CustomerID) ON DELETE CASCADE,
    Amount DECIMAL(18, 2) NOT NULL,
    PaymentDate DATETIME DEFAULT GETDATE(),
    PaymentMethod NVARCHAR(50), -- 'Credit Card', 'Bank Transfer'
    Status NVARCHAR(50) -- 'Completed', 'Pending', 'Failed'
);

-- Accident History Table
CREATE TABLE AccidentHistory (
    AccidentID INT PRIMARY KEY IDENTITY,
    CustomerID INT NOT NULL FOREIGN KEY REFERENCES Customers(CustomerID) ON DELETE CASCADE,
    AccidentDate DATETIME NOT NULL,
    Description NVARCHAR(MAX),
    Status NVARCHAR(50) -- 'Resolved', 'Pending'
);

-- Punishment History Table
CREATE TABLE PunishmentHistory (
    PunishmentID INT PRIMARY KEY IDENTITY,
    CustomerID INT NOT NULL FOREIGN KEY REFERENCES Customers(CustomerID) ON DELETE CASCADE,
    PunishmentDate DATETIME DEFAULT GETDATE(),
    Description NVARCHAR(MAX),
    Status NVARCHAR(50) -- 'Resolved', 'Pending'
);

-- Compensation History Table
CREATE TABLE CompensationHistory (
    CompensationID INT PRIMARY KEY IDENTITY,
    CustomerID INT NOT NULL FOREIGN KEY REFERENCES Customers(CustomerID) ON DELETE CASCADE,
    CompensationDate DATETIME DEFAULT GETDATE(),
    Amount DECIMAL(18, 2) NOT NULL,
    Description NVARCHAR(MAX)
);

-- Compensation Requests Table
CREATE TABLE CompensationRequests (
    RequestID INT PRIMARY KEY IDENTITY,
    CustomerID INT NOT NULL FOREIGN KEY REFERENCES Customers(CustomerID) ON DELETE CASCADE,
    RequestDate DATETIME DEFAULT GETDATE(),
    Status NVARCHAR(50), -- 'Pending', 'Approved', 'Rejected'
    Amount DECIMAL(18, 2) NOT NULL,
    Description NVARCHAR(MAX)
);
