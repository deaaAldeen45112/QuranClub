<?php
  class Surah {
    // DB Stuff
    private $conn;
    private $table = 'surah';

    // Properties
    public $surah_id;
   
    public $surah_name;
    public $surah_quran_part_the_number_of_verses;
    // Constructor with DB
    public function __construct($db) {
      $this->conn = $db;
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
    // Get Single Category
  public function read_by_surah_id(){
    // Create query
    $query = 'SELECT * From ' . $this->table . ' WHERE surah_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->surah_id);

      // Execute query
      $stmt->execute();
      return $stmt;
  }
/*  public function read_by_quran_part_id(){
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
*/
  // Create Category
  public function create() {
    // Create Query
    $query = 'INSERT INTO ' .
      $this->table . '
    SET
    surah_name=:surah_name,
    surah_quran_part_the_number_of_verses=:surah_quran_part_the_number_of_verses
    ';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
 $this->surah_name = htmlspecialchars(strip_tags($this->surah_name));
 $this->surah_quran_part_the_number_of_verses = htmlspecialchars(strip_tags($this->surah_quran_part_the_number_of_verses));

  //$this->techer_id=null;


  // Bind data
  $stmt-> bindParam(':surah_name', $this->surah_name);
  $stmt-> bindParam(':surah_quran_part_the_number_of_verses', $this->surah_quran_part_the_number_of_verses);
 
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
     
      surah_name =:surah_name,
      surah_quran_part_the_number_of_verses=:surah_quran_part_the_number_of_verses
      WHERE
      surah_id=:surah_id
      '
      ;

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->surah_id = htmlspecialchars(strip_tags($this->surah_id));
  $this->surah_name = htmlspecialchars(strip_tags($this->surah_name));
  $this->surah_quran_part_the_number_of_verses = htmlspecialchars(strip_tags($this->surah_quran_part_the_number_of_verses));
 //$this->techer_id=null;
 
 
   // Bind data
   $stmt-> bindParam(':surah_name', $this->surah_name);
   $stmt-> bindParam(':surah_quran_part_the_number_of_verses', $this->surah_quran_part_the_number_of_verses);
  
  // Bind data
   $stmt-> bindParam(':surah_id', $this->surah_id);

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
    $query = 'DELETE FROM ' . $this->table . ' WHERE surah_id = :id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->surah_id = htmlspecialchars(strip_tags($this->surah_id));

    // Bind Data
    $stmt-> bindParam(':id', $this->surah_id);

    // Execute query
    if($stmt->execute()) {
      return true;
    }

    // Print error if something goes wrong
    printf("Error: $s.\n", $stmt->error);

    return false;
    }
  }