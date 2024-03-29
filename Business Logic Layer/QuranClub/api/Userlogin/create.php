<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  
  include_once '../../config/Database.php';
  include_once '../../models/Userlogin.php';
  include_once '../../config/Constants.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog post object
  $userlogin = new Userlogin($db);

  // Get raw posted data
  $data = json_decode(file_get_contents("php://input"));
  $userlogin->name = $data->fullName;
  $userlogin->phone=$data->phone;
  $userlogin->password=$data->password;
  $userlogin->email=$data->email;
  $userlogin->role_name=$data->roleName;
  $userlogin->age=$data->age;

  // Create post
  if($userlogin->create()) {
    echo json_encode(
      array(Constants::$nameOfstateMessge=>Constants::$successOfstateMessge)
    );
  } else {
    echo json_encode(
      array(Constants::$nameOfstateMessge=>Constants::$errorOfstateMessge)
    );
  }
  }
 else{ echo "not found"; }
