<?php
  class Classes {
    // DB Stuff
    private $conn;
    private $table = 'class';

    // Properties
    public $class_id;
    public $class_date_created;
    public $techer_id;
    public $class_name;
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
  public function read_by_class_id(){
    // Create query
    $query = 'SELECT * From ' . $this->table . ' WHERE class_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->class_id);

      // Execute query
      $stmt->execute();
      return $stmt;
    
    }

    public function read_by_techer_id(){
      // Create query
      $query = 'SELECT * From ' . $this->table . ' WHERE techer_id = ?';
  
        //Prepare statement
        $stmt = $this->conn->prepare($query);
  
        // Bind ID
        $stmt->bindParam(1, $this->techer_id);
  
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
    techer_id=:techer_id,
    class_name=:class_name,
    class_date_created=:class_date_created
      ';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  if ($this->techer_id!=null)
  $this->techer_id = htmlspecialchars(strip_tags($this->techer_id));
  $this->class_name = htmlspecialchars(strip_tags($this->class_name));
  $this->class_date_created = htmlspecialchars(strip_tags($this->class_date_created));
  //$this->techer_id=null;


  // Bind data
  $stmt-> bindParam(':class_name', $this->class_name);
  $stmt-> bindParam(':techer_id', $this->techer_id);
  $stmt-> bindParam(':class_date_created', $this->class_date_created);
 

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
      techer_id=:techer_id,
      class_name=:class_name,
      class_date_created=:class_date_created
      WHERE
      class_id=:class_id
      '
      ;

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  if ($this->techer_id!=null)
  $this->techer_id = htmlspecialchars(strip_tags($this->techer_id));
  $this->class_name = htmlspecialchars(strip_tags($this->class_name));
  $this->class_id = htmlspecialchars(strip_tags($this->class_id));
  $this->class_date_created = htmlspecialchars(strip_tags($this->class_date_created));
 
  // Bind data
  $stmt-> bindParam(':techer_id',  $this->techer_id);
  $stmt-> bindParam(':class_name', $this->class_name);
  $stmt-> bindParam(':class_id', $this->class_id);
  $stmt-> bindParam(':class_date_created', $this->class_date_created);
 
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
    $query = 'DELETE FROM ' . $this->table . ' WHERE class_id = :id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->class_id = htmlspecialchars(strip_tags($this->class_id));

    // Bind Data
    $stmt-> bindParam(':id', $this->class_id);

    // Execute query
    if($stmt->execute()) {
      return true;
    }

    // Print error if something goes wrong
    printf("Error: $s.\n", $stmt->error);

    return false;
    }
  }