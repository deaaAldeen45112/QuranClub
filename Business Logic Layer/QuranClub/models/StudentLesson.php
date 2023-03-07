<?php
  class StudentLesson {
    // DB Stuff
    private $conn;
    private $table = 'student_lesson';

    // Properties
    public $student_lesson_id;
    public $student_lesson_note;
    public $lesson_id;
    public $student_id;
   // student_lesson_id	surah_id	from_verse	to_verse	student_lesson_note	lesson_id	student_id	

    // Constructor with DB
    public function __construct($db) {
      $this->conn = $db;
    }

    // Get categories
    public function read() {
      // Create query
      $query = 'SELECT * FROM ' . $this->table;

      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Execute query
      $stmt->execute();

      return $stmt;
    }

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
  }
  // Create Category
  public function create() {
    // Create Query
    $query = 'INSERT INTO ' .
      $this->table . '
    SET
      student_lesson_note = :student_lesson_note,
      lesson_id =:lesson_id,
      student_id=:student_id';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->lesson_id = htmlspecialchars(strip_tags($this->lesson_id));
  $this->student_lesson_note = htmlspecialchars(strip_tags($this->student_lesson_note));
  $this->student_id = htmlspecialchars(strip_tags($this->student_id));
  
  // Bind data
   $stmt-> bindParam(':student_lesson_note', $this->student_lesson_note);
  $stmt-> bindParam(':lesson_id', $this->lesson_id);
  $stmt-> bindParam(':student_id', $this->student_id);
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
      student_lesson_note =:student_lesson_note,
      lesson_id =:lesson_id,
      student_id=:student_id
   
      WHERE
      student_lesson_id =:student_lesson_id';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->lesson_id = htmlspecialchars(strip_tags($this->lesson_id));
  $this->student_lesson_note = htmlspecialchars(strip_tags($this->student_lesson_note));
  $this->student_id = htmlspecialchars(strip_tags($this->student_id));
  $this->student_lesson_id = htmlspecialchars(strip_tags($this->student_lesson_id));
 
  // Bind data
  
  $stmt-> bindParam(':student_lesson_note', $this->student_lesson_note);
  $stmt-> bindParam(':lesson_id', $this->lesson_id);
  $stmt-> bindParam(':student_id', $this->student_id);
  $stmt-> bindParam(':student_lesson_id', $this->student_lesson_id);

 
  // Execute query
  if($stmt->execute()) {
    return true;
  }

  // Print error if something goes wrong
  printf("Error: $s.\n", $stmt->error);

  return false;
  }


  public function update_student_lesson_note_by_student_lesson_id() {
    // Create Query
    $query = 'UPDATE ' .
      $this->table . '
      SET
      student_lesson_note =:student_lesson_note
      WHERE
      student_lesson_id =:student_lesson_id';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->student_lesson_note = htmlspecialchars(strip_tags($this->student_lesson_note));
  $this->student_lesson_id = htmlspecialchars(strip_tags($this->student_lesson_id));
 
  // Bind data
  
  $stmt-> bindParam(':student_lesson_note', $this->student_lesson_note);
  $stmt-> bindParam(':student_lesson_id', $this->student_lesson_id);

 
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
    $query = 'DELETE FROM ' . $this->table . ' WHERE student_lesson_id = :student_lesson_id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->student_lesson_id = htmlspecialchars(strip_tags($this->student_lesson_id));

    // Bind Data
    $stmt-> bindParam(':student_lesson_id', $this->student_lesson_id);

    // Execute query
    if($stmt->execute()) {
      return true;
    }

    // Print error if something goes wrong
    printf("Error: $s.\n", $stmt->error);

    return false;
    }





    
  }