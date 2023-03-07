
<?php
  class StudentQuranPart {
    // DB Stuff
    private $conn;
    private $table = 'student_quran_part';

    // Properties
    public $student_id;
    public $student_quran_part_id;
    public $quran_part_id;
   
   
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

    public function read_join_with_QuranPart_by_student_id(){
      // Create query
      $query = 'SELECT student_quran_part.student_quran_part_id,quran_part.quran_part_number From student_quran_part INNER JOIN quran_part ON student_quran_part.quran_part_id=quran_part.quran_part_id and student_id = ?;';
  
        //Prepare statement
        $stmt = $this->conn->prepare($query);
  
        // Bind ID
        $stmt->bindParam(1, $this->student_id);
  
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
  public function read_by_student_quran_part_id(){
    // Create query
    $query = 'SELECT * From ' . $this->table . ' WHERE student_quran_part_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->student_quran_part_id);

      // Execute query
      $stmt->execute();

      return $stmt;
     
  }
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
    $query = 'INSERT INTO ' .$this->table . ' 
     SET
     student_id =:student_id,
     quran_part_id=:quran_part_id ';
   // Prepare Statement
    $stmt = $this->conn->prepare($query);

  // Clean data
    $this->student_quran_part_id = htmlspecialchars(strip_tags($this->student_id));
    $this->quran_part_id = htmlspecialchars(strip_tags($this->quran_part_id));

    $stmt-> bindParam(':quran_part_id', $this->quran_part_id);
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
      student_id = :student_id ,
      quran_part_id=:quran_part_id 
      WHERE
      student_quran_part_id = :student_quran_part_id
     ';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

 // Clean data
 $this->student_quran_part_id = htmlspecialchars(strip_tags($this->student_quran_part_id));
 $this->quran_part_id = htmlspecialchars(strip_tags($this->quran_part_id));

 $this->student_id = htmlspecialchars(strip_tags($this->student_id));

 $stmt-> bindParam(':quran_part_id', $this->quran_part_id);
 $stmt-> bindParam(':student_quran_part_id', $this->student_quran_part_id);
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
    $query = 'DELETE FROM ' . $this->table . ' WHERE  student_quran_part_id =:student_quran_part_id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->student_quran_part_id = htmlspecialchars(strip_tags($this->student_quran_part_id));

    // Bind Data
    $stmt-> bindParam(':student_quran_part_id', $this->student_quran_part_id);

    // Execute query
    if($stmt->execute()) {
      return true;
    }

    // Print error if something goes wrong
    printf("Error: $s.\n", $stmt->error);

    return false;
    }
  }