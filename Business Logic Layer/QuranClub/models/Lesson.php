<?php
  class Lesson {
    // DB Stuff
    private $conn;
    private $table = 'lesson';

    // Properties
    public $lesson_id;
    public $class_id;
    public $lesson_date_created;
 
    public $lesson_name;
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


    public function read_lesson_by_class_id($class_id){
       $query = 'SELECT * FROM ' . $this->table.' WHERE class_id= ? ';
       $stmt = $this->conn->prepare($query);
       $class_id = htmlspecialchars(strip_tags($class_id));
  // Bind data
       $stmt-> bindParam(1,$class_id);
  // Execute query
  if($stmt->execute()) {
    return $stmt;
  }


    }

  
    // Get Single Category
  public function read_by_lesson_id(){
    // Create query
    $query = 'SELECT * From ' . $this->table . ' WHERE lesson_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->lesson_id);

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
    
    lesson_name=:lesson_name,
    class_id=:class_id,
    lesson_date_created=:lesson_date_created
      ';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
 $this->lesson_name = htmlspecialchars(strip_tags($this->lesson_name));
 $this->lesson_date_created = htmlspecialchars(strip_tags($this->lesson_date_created));
 $this->class_id = htmlspecialchars(strip_tags($this->class_id));
  //$this->techer_id=null;


  // Bind data
  $stmt-> bindParam(':class_id', $this->class_id);
  $stmt-> bindParam(':lesson_name', $this->lesson_name);
  $stmt-> bindParam(':lesson_date_created', $this->lesson_date_created);
 

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
      lesson_date_created=:lesson_date_created,
      lesson_name=:lesson_name,
      class_id=:class_id
      WHERE
      lesson_id=:lesson_id
      '
      ;

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->lesson_name = htmlspecialchars(strip_tags($this->lesson_name));
  $this->lesson_id = htmlspecialchars(strip_tags($this->lesson_id));
  $this->lesson_date_created = htmlspecialchars(strip_tags($this->lesson_date_created));
  $this->class_id = htmlspecialchars(strip_tags($this->class_id));



  // Bind data
  $stmt-> bindParam(':class_id', $this->class_id);
  $stmt-> bindParam(':lesson_name', $this->lesson_name);
  $stmt-> bindParam(':lesson_id', $this->lesson_id);
  $stmt-> bindParam(':lesson_date_created', $this->lesson_date_created);
 

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
    $query = 'DELETE FROM ' . $this->table . ' WHERE lesson_id = :id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->lesson_id = htmlspecialchars(strip_tags($this->lesson_id));

    // Bind Data
    $stmt-> bindParam(':id', $this->lesson_id);

    // Execute query
    if($stmt->execute()) {
      return true;
    }

    // Print error if something goes wrong
    printf("Error: $s.\n", $stmt->error);

    return false;
    }
  }