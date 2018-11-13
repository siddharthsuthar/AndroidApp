<?php
  
require_once 'update_user_info.php';
$db = new update_user_info();
  
// json response array
$response = array("error" => FALSE);

if (isset($_POST['name']) && isset($_POST['programmingLanguages']) && isset($_POST['tools']) && isset($_POST['FrameWorks']) && isset($_POST['Databases'])) {  //continue from here