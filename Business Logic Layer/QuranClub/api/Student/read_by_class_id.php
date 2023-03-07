<?php
if ($_SERVER['REQUEST_METHOD'] === 'GET') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/Student.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate category object
  $student = new Student($db);
  $student->class_id=$_GET['classId'];
  // Category read query
  $result = $student->read_by_class_id();
  
  // Get row count
  $num = $result->rowCount();

  $posts_arr = array();
  $posts_arr['data'] = array();

  // Check if any categories
  if($num > 0) {
       
    
        while($row = $result->fetch(PDO::FETCH_ASSOC)) {
          extract($row);

          $post_item = array(
            'studentId' => $student_id, 
            'userloginId' => $userlogin_id, 
            'fullName'=>$full_name,
            'numberOfDauan' => $number_of_dauan
           
          );

         // Push to "data"
  array_push($posts_arr['data'], $post_item);
     
  // array_push($posts_arr['data'], $post_item);
}
$posts_arr[Constants::$nameOfstateMessge]=Constants::$successOfstateMessge;

// Turn to JSON & outpu    

} 
else {
// No Posts
$posts_arr[Constants::$nameOfstateMessge]=Constants::$errorOfstateMessge;
}
echo json_encode($posts_arr,JSON_UNESCAPED_UNICODE);
 } else {echo "not found";}

?>
