<?php
if ($_SERVER['REQUEST_METHOD'] === 'GET') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/Lesson.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate category object
  $lesson = new Lesson($db);

  // Category read query
  
  // Get raw posted data2
  
  $classId=$_GET['classId'];
  $result = $lesson->read_lesson_by_class_id($classId);
  
  // Get row count
  $num = $result->rowCount();

  // Check if any categories
  $posts_arr = array();
  $posts_arr['data'] = array();

  if($num > 0) {
        // Cat array
       
        while($row = $result->fetch(PDO::FETCH_ASSOC)) {
          extract($row);

          $post_item = array(
            'lessonId' => $lesson_id,  
            'name' => $lesson_name,
            'dateCreated' =>$lesson_date_created,
            'classId'=>  $class_id
          );

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
