<?php
  class QuranPartSurah {
    // DB Stuff
    private $conn;
    private $table = 'quran_part_surah';

    // Properties
    public $quran_part_surah_id;
    public $quran_part_id;
    public $surah_id;
    public $quran_part_surah_from;
    public $quran_part_surah_to;
    public $surah_name;
    public $quran_part_number;
   
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
    /*
    SELECT
  quran_part_surah.quran_part_surah_id,
  surah.surah_id,
  surah.surah_name,
  quran_part.quran_part_number,
  quran_part_surah.quran_part_surah_from,
  quran_part_surah.quran_part_surah_to
  
FROM quran_part_surah
JOIN quran_part
  ON quran_part.quran_part_id = quran_part_surah.quran_part_id
JOIN surah
  ON surah.surah_id = quran_part_surah.surah_id;*/ 
    // Get Single Category
  public function read_by_quran_part_surah_id(){
    // Create query
    $query = 'SELECT * From ' . $this->table . ' WHERE quran_part_surah_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->quran_part_surah_id);

      // Execute query
      $stmt->execute();
      return $stmt;
  }
  
  public function read_quran_part_surah_join_quran_part_join_surah(){
    // Create query
    $query = ' SELECT
    quran_part_surah.quran_part_surah_id,
    surah.surah_id,
    surah.surah_name,
    quran_part.quran_part_number,
    quran_part_surah.quran_part_surah_from,
    quran_part_surah.quran_part_surah_to
    FROM quran_part_surah
    JOIN quran_part
    ON quran_part.quran_part_id = quran_part_surah.quran_part_id
    JOIN surah
    ON surah.surah_id = quran_part_surah.surah_id;';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

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
    quran_part_id=:quran_part_id,
    surah_id=:surah_id,
    quran_part_surah_from=:quran_part_surah_from,
    quran_part_surah_to=:quran_part_surah_to
    ';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
 $this->quran_part_id = htmlspecialchars(strip_tags($this->quran_part_id));
 $this->surah_id = htmlspecialchars(strip_tags($this->surah_id));
 $this->quran_part_surah_from = htmlspecialchars(strip_tags($this->quran_part_surah_from));
 $this->quran_part_surah_to = htmlspecialchars(strip_tags($this->quran_part_surah_to));
  //$this->techer_id=null;


  // Bind data
  $stmt-> bindParam(':quran_part_id', $this->quran_part_id);
  $stmt-> bindParam(':surah_id', $this->surah_id);
  $stmt-> bindParam(':quran_part_surah_from', $this->quran_part_surah_from);
  $stmt-> bindParam(':quran_part_surah_to', $this->quran_part_surah_to);
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
      quran_part_id=:quran_part_id,
      surah_id=:surah_id,
      quran_part_surah_from=:quran_part_surah_from,
      quran_part_surah_to=:quran_part_surah_to
      WHERE
      quran_part_surah_id=:quran_part_surah_id
      '
      ;

  // Prepare Statement
  $stmt = $this->conn->prepare($query);
  // Clean data
  $this->quran_part_id = htmlspecialchars(strip_tags($this->quran_part_id));
  $this->surah_id = htmlspecialchars(strip_tags($this->surah_id));
  $this->quran_part_surah_from = htmlspecialchars(strip_tags($this->quran_part_surah_from));
  $this->quran_part_surah_to = htmlspecialchars(strip_tags($this->quran_part_surah_to));
  $this->quran_part_surah_id = htmlspecialchars(strip_tags($this->quran_part_surah_id));

   // Bind Data
   $stmt-> bindParam(':quran_part_surah_id', $this->quran_part_surah_id);
   $stmt-> bindParam(':quran_part_id', $this->quran_part_id);
   $stmt-> bindParam(':surah_id', $this->surah_id);
   $stmt-> bindParam(':quran_part_surah_from', $this->quran_part_surah_from);
   $stmt-> bindParam(':quran_part_surah_to', $this->quran_part_surah_to);
  
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
    $query = 'DELETE FROM ' . $this->table . ' WHERE  quran_part_surah_id =:quran_part_surah_id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->quran_part_surah_id = htmlspecialchars(strip_tags($this->quran_part_surah_id));

    // Bind Data
    $stmt-> bindParam(':quran_part_surah_id', $this->quran_part_surah_id);

    // Execute query
    if($stmt->execute()) {
      return true;
    }

    // Print error if something goes wrong
    printf("Error: $s.\n", $stmt->error);

    return false;
    }
  }