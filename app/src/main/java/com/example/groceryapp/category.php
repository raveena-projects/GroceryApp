<?php
$mysqli = new mysqli("localhost", "root", "", "grocery_db");

if ($mysqli->connect_error) {
    die("Connection failed: " . $mysqli->connect_error);
}

//category
//items
$query = "
    SELECT
           categories.category_id,
           categories.category_name AS name,
           COUNT(items.item_id) AS itemCount
       FROM categories
       LEFT JOIN items ON categories.category_id = items.category_id
       GROUP BY categories.category_id, categories.category_name
";

$result = $mysqli->query($query);

if ($result) {
    $categories = array();
    while ($row = $result->fetch_assoc()) {
        $categories[] = $row;
    }
    header('Content-Type: application/json');
    echo json_encode($categories);
} else {
    echo json_encode(["error" => "Error fetching categories"]);
}

$mysqli->close();
?>
