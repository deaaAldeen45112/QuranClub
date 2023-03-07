<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: POST');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

  include_once '../../config/Database.php';
  include_once '../../models/StudentLessonQuranPartSurah.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog post object
  $student_lesson_quran_part_surah = new StudentLessonQuranPartSurah($db);

  // Get raw posted data
  $data = json_decode(file_get_contents("php://input"));
    // Properties
    
  $student_lesson_quran_part_surah->student_lesson_id=$data->studentLessonId;
  $student_lesson_quran_part_surah->quran_part_surah_id=$data->quranPartSurahId;
  $student_lesson_quran_part_surah->from_verses=$data->fromVerses;
  $student_lesson_quran_part_surah->to_verses=$data->toVerses;
  // Create post
  if($student_lesson_quran_part_surah->create()) {
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
