<?php
$servername = "localhost";
$username = "your_mysql_username";
$password = "your_mysql_password";
$dbname = "your_database_name";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$email = $_POST['email'];
$password = $_POST['password'];

$sql = "SELECT * FROM users WHERE email = '$email' AND password = '$password'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $response = array("status" => "success", "message" => "Login successful!");
    echo json_encode($response);
} else {
    $response = array("status" => "error", "message" => "Invalid email or password");
    echo json_encode($response);
}

$conn->close();
?>
