<?php
  class Student {
    // DB Stuff
    private $conn;
    private $table = 'student';

    // Properties
    public $student_id;
    public $userlogin_id;
    public $number_of_dauan;
    public $class_id;
   
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
  public function read_by_student_id(){
    // Create query
    $query = 'SELECT * From ' . $this->table . ' WHERE student_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->student_id);

      // Execute query
      $stmt->execute();
      return $stmt;
  }

  public function read_by_email($email){
    // Create query
      $query =  'SELECT student_id ,student.userlogin_id ,number_of_dauan,class_id From '. $this->table. ' student JOIN userlogin ON student.userlogin_id=userlogin.userlogin_id  WHERE userlogin.email = ?';
      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1,$email );

      // Execute query
      $stmt->execute();
      return $stmt;
  }

  
  public function read_by_class_id(){
    // Create query
    $query = 'SELECT userlogin.full_name, student.student_id, student.number_of_dauan,student.userlogin_id
    FROM (userlogin
    INNER JOIN student ON student.userlogin_id = userlogin.userlogin_id
    and student.class_id=?
          )';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->class_id);

      // Execute query
      $stmt->execute();
      return $stmt;
  }

 
  public function read_saved_percentage_for_each_part(){
    // Create query
    $query = "SELECT quran_part.quran_part_number ,\n"

    . "sum(student_lesson_quran_part_surah.to_verses-student_lesson_quran_part_surah.from_verses)/ quran_part.quran_part_the_number_of_verses\n"
  
    . "as \"saved_percentage_of_verses\" \n"
  
    . "FROM student_lesson JOIN student_lesson_quran_part_surah \n"
  
    . "on student_lesson.student_lesson_id=student_lesson_quran_part_surah.student_lesson_id JOIN quran_part_surah\n"
  
    . "on quran_part_surah.quran_part_surah_id =student_lesson_quran_part_surah.quran_part_surah_id \n"
  
    . "JOIN quran_part on quran_part.quran_part_id=quran_part_surah.quran_part_id WHERE student_lesson.student_id=? GROUP BY quran_part.quran_part_number ORDER BY quran_part.quran_part_number ASC;";
  

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->student_id);

      // Execute query
      $stmt->execute();
      return $stmt;
  }

  public function read_by_userlogin_id(){
    // Create query
    $query = 'SELECT * From ' . $this->table . ' WHERE userlogin_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->userlogin_id);

      // Execute query
      $stmt->execute();
      return $stmt;
  }
  // Create Category
  public function create() {
    // Create Query
    $query = 'INSERT INTO ' .$this->table . ' SET userlogin_id = :userlogin_id 
    ,class_id=:class_id ';
   // Prepare Statement
    $stmt = $this->conn->prepare($query);
  // Clean data
    $this->userlogin_id = htmlspecialchars(strip_tags($this->userlogin_id));
   if( $this->class_id!=null)
    $this->class_id = htmlspecialchars(strip_tags($this->class_id));  
    $stmt-> bindParam(':class_id', $this->class_id);
    $stmt-> bindParam(':userlogin_id', $this->userlogin_id);

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
      $this->table . ' SET userlogin_id = :userlogin_id ,class_id=:class_id 
      WHERE
      student_id = :student_id';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

 // Clean data
 $this->userlogin_id = htmlspecialchars(strip_tags($this->userlogin_id));
 if( $this->class_id!=null)
 $this->class_id = htmlspecialchars(strip_tags($this->class_id));
 $this->student_id = htmlspecialchars(strip_tags($this->student_id));

 $stmt-> bindParam(':class_id', $this->class_id);
 $stmt-> bindParam(':userlogin_id', $this->userlogin_id);
 $stmt-> bindParam(':student_id', $this->student_id);

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
    $query = 'DELETE FROM ' . $this->table . ' WHERE student_id = :student_id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->student_id = htmlspecialchars(strip_tags($this->student_id));

    // Bind Data
    $stmt-> bindParam(':student_id', $this->student_id);

    // Execute query
    if($stmt->execute()) {
      return true;
    }

    // Print error if something goes wrong
    printf("Error: $s.\n", $stmt->error);

    return false;
    }
  }