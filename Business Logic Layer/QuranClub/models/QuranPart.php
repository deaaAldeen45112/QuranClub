<?php
  class QuranPart {
    // DB Stuff
    private $conn;
    private $table = 'quran_part';

    // Properties
    public $quran_part_id;
    public $quran_part_number;
    public $quran_part_the_number_of_verses;
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
  public function read_by_quran_part_id(){
    // Create query
    $query = 'SELECT * From ' . $this->table . ' WHERE quran_part_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->quran_part_id);

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
    quran_part_number=:quran_part_number,
    quran_part_the_number_of_verses=:quran_part_the_number_of_verses
    ';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
 $this->quran_part_number = htmlspecialchars(strip_tags($this->quran_part_number));
 $this->quran_part_the_number_of_verses = htmlspecialchars(strip_tags($this->quran_part_the_number_of_verses));

  //$this->techer_id=null;


  // Bind data
  $stmt-> bindParam(':quran_part_number', $this->quran_part_number);
  $stmt-> bindParam(':quran_part_the_number_of_verses', $this->quran_part_the_number_of_verses);
 

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
      quran_part_number=:quran_part_number,
      quran_part_the_number_of_verses=:quran_part_the_number_of_verses
      WHERE
      quran_part_id=:quran_part_id
      '
      ;

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->quran_part_id = htmlspecialchars(strip_tags($this->quran_part_id));
  $this->quran_part_number = htmlspecialchars(strip_tags($this->quran_part_number));
  $this->quran_part_the_number_of_verses = htmlspecialchars(strip_tags($this->quran_part_the_number_of_verses));
 
   //$this->techer_id=null;
 
 
   // Bind data
   $stmt-> bindParam(':quran_part_number', $this->quran_part_number);
   $stmt-> bindParam(':quran_part_the_number_of_verses', $this->quran_part_the_number_of_verses);
  
  // Bind data
   $stmt-> bindParam(':quran_part_id', $this->quran_part_id);

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
    $query = 'DELETE FROM ' . $this->table . ' WHERE quran_part_id = :id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->quran_part_id = htmlspecialchars(strip_tags($this->quran_part_id));

    // Bind Data
    $stmt-> bindParam(':id', $this->quran_part_id);

    // Execute query
    if($stmt->execute()) {
      return true;
    }

    // Print error if something goes wrong
    printf("Error: $s.\n", $stmt->error);

    return false;
    }
  }