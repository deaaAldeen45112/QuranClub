<?php
if ($_SERVER['REQUEST_METHOD'] === 'DELETE') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: DELETE');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
  include_once '../../config/Constants.php';
  include_once '../../config/Database.php';
  include_once '../../models/StudentLessonQuranPartSurah.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog post object
  $student_lesson_quran_part_surah = new StudentLessonQuranPartSurah($db);
  $student_lesson_quran_part_surah->student_lesson_quran_part_surah_id=$_GET['studentLessonQuranPartSurahId'];
  

  if($student_lesson_quran_part_surah->delete()) {
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
