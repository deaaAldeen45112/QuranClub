
<?php
class Techer {
  // DB Stuff
  private $conn;
  private $table = 'teacher';

  // Properties
  public $teacher_id;
  public $userlogin_id;
 
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
public function read_by_teacher_id(){
  // Create query
  $query = 'SELECT * From ' . $this->table . ' WHERE teacher_id = ?';

    //Prepare statement
    $stmt = $this->conn->prepare($query);

    // Bind ID
    $stmt->bindParam(1, $this->teacher_id);

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
  $query = 'INSERT INTO ' .$this->table . ' SET userlogin_id = :userlogin_id';
 // Prepare Statement
  $stmt = $this->conn->prepare($query);

// Clean data
 
  $this->userlogin_id = htmlspecialchars(strip_tags($this->userlogin_id));

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
    $this->table . ' SET
    userlogin_id = :userlogin_id
    WHERE
    teacher_id = :teacher_id';

// Prepare Statement
$stmt = $this->conn->prepare($query);

// Clean data
$this->teacher_id = htmlspecialchars(strip_tags($this->teacher_id));
$this->userlogin_id = htmlspecialchars(strip_tags($this->userlogin_id));
// Bind data
$stmt-> bindParam(':teacher_id', $this->teacher_id);
$stmt-> bindParam(':userlogin_id', $this->userlogin_id);
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
  $query = 'DELETE FROM ' . $this->table . ' WHERE teacher_id = :teacher_id';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // clean data
  $this->id = htmlspecialchars(strip_tags($this->teacher_id));

  // Bind Data
  $stmt-> bindParam(':teacher_id', $this->teacher_id);

  // Execute query
  if($stmt->execute()) {
    return true;
  }

  // Print error if something goes wrong
  printf("Error: $s.\n", $stmt->error);

  return false;
  }
}