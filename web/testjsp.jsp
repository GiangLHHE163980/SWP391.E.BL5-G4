<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DataTables.Net</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Datatables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="./assets/css/style.css">
</head>
<body>
<div class="wrapper d-flex">
    <!-- Sidebar -->
    <nav class="sidebar bg-dark">
        <div class="sidebar-header text-center text-white py-4">
            <h4><span style="color: #FF69B4;">Giang</span>Admin</h4>
        </div>
        <ul class="list-unstyled px-2">
            <li><a href="#" class="text-white d-block py-2 px-3"><i class="fas fa-home me-2"></i> Dashboard</a></li>
            <li>
                <a href="#tablesSubmenu" data-bs-toggle="collapse" aria-expanded="false" class="dropdown-toggle text-white d-block py-2 px-3">
                    <i class="fas fa-table me-2"></i> Tables
                </a>
                <ul class="collapse list-unstyled ps-4" id="tablesSubmenu">
                    <li><a href="#" class="text-white d-block py-2">Basic Table</a></li>
                    <li><a href="#" class="text-white d-block py-2">Datatables</a></li>
                </ul>
            </li>
            <li><a href="#" class="text-white d-block py-2 px-3"><i class="fas fa-chart-bar me-2"></i> Charts</a></li>
        </ul>
    </nav>

    <!-- Main Content -->
    <div class="main-content flex-grow-1 bg-light">
        <!-- Header -->
        <div class="header bg-white d-flex justify-content-between align-items-center p-3 border-bottom">
            <div class="d-flex align-items-center">
                <h4 class="mb-0">DataTables.Net</h4>
                <input type="text" class="form-control ms-3 search-input" placeholder="Search..." style="width: 300px;">
            </div>
           <div class="dropdown profile-dropdown">
                    <a href="#" class="d-flex align-items-center text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="https://via.placeholder.com/40" alt="Profile Picture">
                        <span class="ms-2">Hi, Hizria</span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="#">My Profile</a></li>
                        <li><a class="dropdown-item" href="#">My Balance</a></li>
                        <li><a class="dropdown-item" href="#">Inbox</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Account Settings</a></li>
                        <li><a class="dropdown-item" href="#">Logout</a></li>
                    </ul>
                </div>
        </div>

        <!-- Breadcrumb -->
        <div class="breadcrumb-container px-3 py-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb mb-0">
                    <li class="breadcrumb-item"><a href="#">Tables</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Datatables</li>
                </ol>
            </nav>
        </div>

        <!-- Table Card -->
        <div class="card mx-3 my-4">
            <div class="card-header bg-white">
                <h5>Basic</h5>
            </div>
            <div class="card-body">
                <table id="datatable" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Position</th>
                        <th>Office</th>
                        <th>Age</th>
                        <th>Start date</th>
                        <th>Salary</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Airi Satou</td>
                        <td>Accountant</td>
                        <td>Tokyo</td>
                        <td>33</td>
                        <td>2008/11/28</td>
                        <td>$162,700</td>
                    </tr>
                    <tr>
                        <td>Angelica Ramos</td>
                        <td>Chief Executive Officer (CEO)</td>
                        <td>London</td>
                        <td>47</td>
                        <td>2009/10/09</td>
                        <td>$1,200,000</td>
                    </tr>
                    <tr>
                        <td>Ashton Cox</td>
                        <td>Junior Technical Author</td>
                        <td>San Francisco</td>
                        <td>66</td>
                        <td>2009/01/12</td>
                        <td>$86,000</td>
                    </tr>
                    <tr>
                        <td>Bradley Greer</td>
                        <td>Software Engineer</td>
                        <td>London</td>
                        <td>41</td>
                        <td>2012/10/13</td>
                        <td>$132,000</td>
                    </tr>
                    <tr>
                        <td>Bruno Nash</td>
                        <td>Software Engineer</td>
                        <td>London</td>
                        <td>38</td>
                        <td>2011/05/03</td>
                        <td>$163,500</td>
                    </tr>
                    <tr>
                        <td>Brielle Williamson</td>
                        <td>Integration Specialist</td>
                        <td>New York</td>
                        <td>61</td>
                        <td>2012/12/02</td>
                        <td>$372,000</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<!-- Datatables -->
<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#datatable').DataTable({
            paging: true,
            searching: true,
            info: true,
            lengthChange: true,
        });
    });
</script>
</body>
</html>