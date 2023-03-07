
<?php
if ($_SERVER['REQUEST_METHOD'] === 'GET') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/StudentLessonQuranPartSurah.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog post object
  $student_lesson_quran_part_surah = new StudentLessonQuranPartSurah($db);

  // Blog post query
  $result = $student_lesson_quran_part_surah->read();
  // Get row count
  $num = $result->rowCount();

  // Check if any posts
  $posts_arr = array();
  $posts_arr['data'] = array();

  if($num > 0) {
    // Post array


     while($row = $result->fetch(PDO::FETCH_ASSOC)) {
      extract($row);
      $post_item = array(
        
       'studentLessonQuranPartSurahId'=>$student_lesson_quran_part_surah_id,
       'studentLessonId'=>$student_lesson_id,
       'quranPpartSurahId'=>$quran_part_surah_id,
       'fromVerses'=>$from_verses,
       'toVerses'=>$to_verses,
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
