<?php
if ($_SERVER['REQUEST_METHOD'] === 'GET') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/StudentQuranPart.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate category object
  $student_quran_part = new StudentQuranPart($db);
  $student_quran_part->student_id=$_GET['studentId'];
  // Category read query
  $result = $student_quran_part->read_join_with_QuranPart_by_student_id();
  
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
            'studentQuranPartId' => $student_quran_part_id,  
            'quranPartNumber' =>$quran_part_number,
          );

          array_push($posts_arr['data'], $post_item);

          // array_push($posts_arr['data'], $post_item);
        }
        $posts_arr[Constants::$nameOfstateMessge]=Constants::$successOfstateMessge;
      
      
      } else {
       
      // No Posts
      $posts_arr[Constants::$nameOfstateMessge]=Constants::$errorOfstateMessge;
      }
      
      
      echo json_encode($posts_arr,JSON_UNESCAPED_UNICODE);
 } else {echo "not found";}
      
     ?> 