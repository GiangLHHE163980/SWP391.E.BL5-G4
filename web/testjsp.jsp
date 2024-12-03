<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Product</title>
        <!--  Bootstrap CSS -->
        <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">-->
        <link href="./assets/css/bootstrap.css" rel="stylesheet">
      


    </head>
    <body>
        <!-- Sidebar và nội dung chính được chia thành 2 cột -->
        <div class="d-flex">
            <!-- Sidebar -->
            <nav class="bg-light border-end" style="width: 250px;">
                <div class="p-3 text-center border-bottom">
                    <h4>TopNotepad</h4>
                </div>
                <ul class="nav flex-column p-2">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Sales</a>
                        <ul class="list-unstyled ps-4">
                            <li><a href="#" class="nav-link">Products</a></li>
                            <li><a href="#" class="nav-link">Clients</a></li>
                            <li><a href="#" class="nav-link">Estimates</a></li>
                            <li><a href="#" class="nav-link">Invoices</a></li>
                            <li><a href="#" class="nav-link">Payments</a></li>
                            <li><a href="#" class="nav-link">Refunds</a></li>
                            <li><a href="#" class="nav-link">Dashboard</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Purchases</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Accounting</a>
                    </li>
                </ul>
            </nav>

            <!-- Main Content -->
            <div class="flex-grow-1">
                <!-- Header -->
                <header class="bg-primary text-white p-3 d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Add Product</h5>
                    <div>
                        <a href="#" class="text-white me-3">Need help?</a>
                        <span>ksxxxxxxxxxx</span>
                    </div>
                </header>

                <!-- Form -->
                <div class="container my-4">
                    <form>
                        <div class="mb-3 row">
                            <label for="productName" class="col-sm-2 col-form-label">Name</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="productName" placeholder="Canon Lens">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="description" class="col-sm-2 col-form-label">Description</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" id="description" rows="3" placeholder="Fixed Focal Length, Zoom Focal Length, Image Stabilization"></textarea>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="unitPrice" class="col-sm-2 col-form-label">Unit Selling Price</label>
                            <div class="col-sm-4">
                                <input type="number" class="form-control" id="unitPrice" placeholder="300">
                            </div>
                            <label for="customsCode" class="col-sm-2 col-form-label">Customs Code</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="customsCode" placeholder="5">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="uniqueCode" class="col-sm-2 col-form-label">Unique Code</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="uniqueCode">
                            </div>
                        </div>
                        <div class="d-flex align-items-center">
                            <button type="submit" class="btn btn-success me-2">Save</button>
                            <button type="reset" class="btn btn-secondary me-2">Reset</button>
                            <button type="button" class="btn btn-danger">Close</button>
                            <div class="dropdown ms-2">
                                <button class="btn btn-success dropdown-toggle" type="button" id="saveOptions" data-bs-toggle="dropdown" aria-expanded="false">
                                    Save
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="saveOptions">
                                    <li><a class="dropdown-item" href="#">Save & Add Purchases</a></li>
                                    <li><a class="dropdown-item" href="#">Save & Add New Product</a></li>
                                    <li><a class="dropdown-item" href="#">Save & Exit</a></li>
                                </ul>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
