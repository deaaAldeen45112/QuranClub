<?php
if ($_SERVER['REQUEST_METHOD'] === 'GET') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/Surah.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate category object
  $surah = new Surah($db);
  $surah->surah_id=$_GET['surahId'];

  // Category read query
  $result =  $surah->read_by_surah_id();
  
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
          
            'surahId' => $surah_id,  
            'name' => $surah_name,
            'surahQuranPartNumberOfVerse' => $surah_quran_part_the_number_of_verses
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