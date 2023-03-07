<?php
if ($_SERVER['REQUEST_METHOD'] === 'PUT') { 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: PUT');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

  include_once '../../config/Database.php';
  include_once '../../models/QuranPartSurah.php';
  include_once '../../config/Constants.php';
  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  /// Instantiate blog post object
  $quranPartSurah = new QuranPartSurah($db);
  // Constructor with DB
  $data = json_decode(file_get_contents("php://input"));
  $quranPartSurah->quran_part_surah_id=$data->quranPartSurahId;
  $quranPartSurah->quran_part_id=$data->quranPartId;
  $quranPartSurah->quran_part_id=$data->quranPartId;
  $quranPartSurah->surah_id=$data->surahId;
  $quranPartSurah->quran_part_surah_from=$data->quranPartSurahFrom;
  $quranPartSurah->quran_part_surah_to=$data->quranPartSurahTo;

if($quranPartSurah->update()) {
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
