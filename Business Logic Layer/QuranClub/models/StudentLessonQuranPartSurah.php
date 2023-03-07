<?php
  class StudentLessonQuranPartSurah {
    // DB Stuff
    private $conn;
    private $table = 'student_lesson_quran_part_surah';

    // Properties
    public $student_lesson_quran_part_surah_id;
    public $student_lesson_id;
    public $quran_part_surah_id;
    public $from_verses;
    public $to_verses;
    public $lesson_id;
    public $student_id;
   
    // Constructor with DB
    public function __construct($db) {
      $this->conn = $db;
    }



  public function read_studentLessonQuranPartSurah_join_studentLesson_join_userlogin_by_student_id()
  {
    $query = "SELECT student_lesson.student_lesson_id,lesson.lesson_name,student_lesson.student_lesson_note, student.student_id,userlogin.full_name,student_lesson_quran_part_surah.student_lesson_quran_part_surah_id,\n"
    . "student_lesson_quran_part_surah.quran_part_surah_id,student_lesson_quran_part_surah.from_verses,\n"
    . "student_lesson_quran_part_surah.to_verses\n"
    . "from student_lesson_quran_part_surah INNER JOIN student_lesson ON student_lesson_quran_part_surah.student_lesson_id=student_lesson.student_lesson_id INNER JOIN lesson ON lesson.lesson_id=student_lesson.lesson_id INNER JOIN student on student_lesson.student_id=student.student_id INNER JOIN userlogin on userlogin.userlogin_id=student.userlogin_id where student_lesson.student_id=? ORDER BY `student_lesson_quran_part_surah`.`student_lesson_id` ASC ;";
      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->student_id);

      // Execute query
      $stmt->execute();

      return $stmt;
  
  }

    // Get categories
    public function read_studentLessonQuranPartSurah_join_studentLesson_join_userlogin() {
      // Create query
      $query = "SELECT student_lesson.student_lesson_id,student_lesson.student_lesson_note, student.student_id,userlogin.full_name,userlogin.phone,student_lesson_quran_part_surah.student_lesson_quran_part_surah_id,\n"
    . "student_lesson_quran_part_surah.quran_part_surah_id,student_lesson_quran_part_surah.from_verses,\n"
    . "student_lesson_quran_part_surah.to_verses\n"
    . "from student_lesson_quran_part_surah INNER JOIN student_lesson ON student_lesson_quran_part_surah.student_lesson_id=student_lesson.student_lesson_id INNER JOIN student on student_lesson.student_id=student.student_id INNER JOIN userlogin on userlogin.userlogin_id=student.userlogin_id where lesson_id=? ORDER BY `student_lesson_quran_part_surah`.`student_lesson_id` ASC ;";
      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->lesson_id);

      // Execute query
      $stmt->execute();

      return $stmt;
    }

    public function read() {
      // Create query
      $query = 'SELECT * FROM ' . $this->table;

      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Execute query
      $stmt->execute();

      return $stmt;
    }




















    /*
    // Get Single Category
  public function read_by_lesson_id(){
    // Create query
    $query = 'SELECT student_lesson.student_lesson_id, userlogin.full_name,student_lesson.student_lesson_note FROM ((userlogin INNER JOIN student ON userlogin.userlogin_id = student.userlogin_id) INNER JOIN student_lesson ON student_lesson.student_id = student.student_id and student_lesson.lesson_id=? );';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->lesson_id);

      // Execute query
      $stmt->execute();

      return $stmt;
  }

  public function read_by_student_id(){
    // Create query
    $query = 'SELECT student_lesson.student_lesson_id,student_lesson.student_lesson_note
    ,lesson.lesson_name
    FROM lesson INNER JOIN student_lesson ON lesson.lesson_id = student_lesson.lesson_id
    and student_lesson.student_id=?
    ;';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->student_id);

      // Execute query
      $stmt->execute();

      return $stmt;
  }
  public function read_by_student_lesson_id(){
    // Create query
    $query = 'SELECT * From ' . $this->table . ' WHERE student_lesson_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->student_id);

      // Execute query
      $stmt->execute();

      return $stmt;
  }*/
  // Create Category
  public function create() {
    // Create Query
    $query = 'INSERT INTO ' .
      $this->table . '
    SET
      student_lesson_id = :student_lesson_id,
      quran_part_surah_id =:quran_part_surah_id,
      from_verses=:from_verses,
      to_verses=:to_verses';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->quran_part_surah_id = htmlspecialchars(strip_tags($this->quran_part_surah_id));
  $this->student_lesson_id = htmlspecialchars(strip_tags($this->student_lesson_id));
  $this->from_verses = htmlspecialchars(strip_tags($this->from_verses));
  $this->to_verses = htmlspecialchars(strip_tags($this->to_verses));
 
  // Bind data
  $stmt-> bindParam(':student_lesson_id', $this->student_lesson_id);
  $stmt-> bindParam(':quran_part_surah_id', $this->quran_part_surah_id);
  $stmt-> bindParam(':from_verses', $this->from_verses);
  $stmt-> bindParam(':to_verses', $this->to_verses);
  // Execute query
  if($stmt->execute()) {
    return true;
  }

  // Print error if something goes wrong
  printf("Error: $s.\n", $stmt->error);

  return false;
  }

  // Update Category
  public function update() {
    // Create Query
    $query = 'UPDATE ' .
      $this->table . '
      SET
      student_lesson_id =:student_lesson_id,
      quran_part_surah_id =:quran_part_surah_id,
      from_verses=:from_verses,
      to_verses=:to_verses
      WHERE
      student_lesson_quran_part_surah_id =:student_lesson_quran_part_surah_id';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->quran_part_surah_id = htmlspecialchars(strip_tags($this->quran_part_surah_id));
  $this->student_lesson_id = htmlspecialchars(strip_tags($this->student_lesson_id));
  $this->from_verses = htmlspecialchars(strip_tags($this->from_verses));
  $this->student_lesson_quran_part_surah_id = htmlspecialchars(strip_tags($this->student_lesson_quran_part_surah_id));
  $this->to_verses = htmlspecialchars(strip_tags($this->to_verses));
 
  // Bind data
  $stmt-> bindParam(':to_verses', $this->to_verses);
  $stmt-> bindParam(':student_lesson_id', $this->student_lesson_id);
  $stmt-> bindParam(':quran_part_surah_id', $this->quran_part_surah_id);
  $stmt-> bindParam(':from_verses', $this->from_verses);
  $stmt-> bindParam(':student_lesson_quran_part_surah_id', $this->student_lesson_quran_part_surah_id);

 
  // Execute query
  if($stmt->execute()) {
    return true;
  }

  // Print error if something goes wrong
  printf("Error: $s.\n", $stmt->error);

  return false;
  }

  // Delete Category
  public function delete() {
    // Create query
    $query = 'DELETE FROM ' . $this->table . ' WHERE student_lesson_quran_part_surah_id = :student_lesson_quran_part_surah_id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->student_lesson_quran_part_surah_id = htmlspecialchars(strip_tags($this->student_lesson_quran_part_surah_id));

    // Bind Data
    $stmt-> bindParam(':student_lesson_quran_part_surah_id', $this->student_lesson_quran_part_surah_id);

    // Execute query
    if($stmt->execute()) {
      return true;
    }

    // Print error if something goes wrong
    printf("Error: $s.\n", $stmt->error);

    return false;
    }
  }