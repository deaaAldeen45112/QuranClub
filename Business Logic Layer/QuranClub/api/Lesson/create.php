<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: POST');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
  include_once '../../config/Constants.php';
  include_once '../../config/Database.php';
  include_once '../../models/Lesson.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog post object
  $lesson = new Lesson($db);

  // Get raw posted data
  $data = json_decode(file_get_contents("php://input"));
  $lesson->lesson_name=$data->name;
  $lesson->class_id=$data->classId;
  $lesson->lesson_date_created=$data->dateCreated;
/*  
  $myfile = fopen("C:\\Users\\user\\Desktop\\deaa.txt", "a") or die("Unable to open file!");
  $txt = $data->dateCreated;
  fwrite($myfile, $txt);
  $txt = "Goofy Goof\n";
  fwrite($myfile, $txt);
  fclose($myfile);
*/

  // Create post
  if($lesson->create()) {
  
    echo json_encode(
      array(Constants::$nameOfstateMessge=>Constants::$successOfstateMessge,Constants::$lastInsertId=>$db->lastInsertId())
    );
  } else {
    echo json_encode(
      array(Constants::$nameOfstateMessge=>Constants::$errorOfstateMessge)
    );
  }
  }
 else{ echo "not found"; }
