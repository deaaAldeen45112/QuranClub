<?php
  class userlogin {
    // DB Stuff
    private $conn;
    private $table = 'userlogin';

    // Properties
    public $id;
    public $name;
    public $email;
    public $password;
    public $phone;
    public $age;
    public $created_date;
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
  public function read_single(){
    // Create query
    $query = 'SELECT * From' . $this->table . ' WHERE userlogin_id = ?';

      //Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->id);

      // Execute query
      $stmt->execute();

      $row = $stmt->fetch(PDO::FETCH_ASSOC);

      // set properties
      $this->id = $row[0];
      $this->name = $row[1];
  }

  // Create Category
  public function create() {
    // Create Query
    $query = 'INSERT INTO ' .
      $this->table . '
    SET
      full_name = : full_name,
      email = :email,
      password = : password,
      phone = : phone,
      age = : age
      ';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->name = htmlspecialchars(strip_tags($this->name));
  $this->email = htmlspecialchars(strip_tags($this->email));
  $this->password = htmlspecialchars(strip_tags($this->password));
  $this->age = htmlspecialchars(strip_tags($this->age));
  $this->phone = htmlspecialchars(strip_tags($this->phone));

  // Bind data
  $stmt-> bindParam(':full_name', $this->name);
  $stmt-> bindParam(':email', $this->email);
  $stmt-> bindParam(':password', $this->password);
  $stmt-> bindParam(':phone', $this->phone);
  $stmt-> bindParam(':age', $this->age);

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
      full_name = : full_name,
      email = :email,
      password = : password,
      phone = : phone,
      age = : age
      WHERE
      userlogin_id = :id';

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Prepare Statement
  $stmt = $this->conn->prepare($query);

  // Clean data
  $this->name = htmlspecialchars(strip_tags($this->name));
  $this->email = htmlspecialchars(strip_tags($this->email));
  $this->password = htmlspecialchars(strip_tags($this->password));
  $this->age = htmlspecialchars(strip_tags($this->age));
  $this->phone = htmlspecialchars(strip_tags($this->phone));

  // Bind data
  $stmt-> bindParam(':full_name', $this->name);
  $stmt-> bindParam(':email', $this->email);
  $stmt-> bindParam(':password', $this->password);
  $stmt-> bindParam(':phone', $this->phone);
  $stmt-> bindParam(':age', $this->age);
  $stmt-> bindParam(':id', $this->id);
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
    $query = 'DELETE FROM ' . $this->table . ' WHERE userlogin_id = :id';

    // Prepare Statement
    $stmt = $this->conn->prepare($query);

    // clean data
    $this->id = htmlspecialchars(strip_tags($this->id));

    // Bind Data
    $stmt-> bindParam(':id', $this->id);

    // Execute query
    if($stmt->execute()) {
      return true;
    }

    // Print error if something goes wrong
    printf("Error: $s.\n", $stmt->error);

    return false;
    }
  }