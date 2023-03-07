<?php
if ($_SERVER['REQUEST_METHOD'] === 'GET') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/QuranPartSurah.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog post object
  $quranPartSurah = new QuranPartSurah($db);
   
  // Category read query
  $result = $quranPartSurah->read_quran_part_surah_join_quran_part_join_surah();
  
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
            'quranPartSurahId' => $quran_part_surah_id,
            'surahId' => $surah_id,
            'quranPartSurahFrom' => $quran_part_surah_from,
            'quranPartSurahTo' => $quran_part_surah_to,
            'surahName' => $surah_name,
            'quranPartNumber' => $quran_part_number
           
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