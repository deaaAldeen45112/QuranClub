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
  $student->student_id=$_GET['studentId'];
  // Category read query
  $result = $student->read_saved_percentage_for_each_part();
  
  // Get row count
  $num = $result->rowCount();
  $posts_arr = array();
  $posts_arr['data'] = array();

  // Check if any categories
  if($num > 0) {
        // Cat array
      
        while($row = $result->fetch(PDO::FETCH_ASSOC)) {
          extract($row);

          $post_item = array(
            'quranPartNumber' => $row['quran_part_number'],
            'savedPercentageOfVerses' => $row['saved_percentage_of_verses']
           
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
