<?php
  
require_once 'update_user_info.php';
$db = new update_user_info();
  
// json response array
$response = array("error" => FALSE);

if (isset($_POST['name']) && isset($_POST['programmingLanguages'])
    && isset($_POST['tools']) && isset($_POST['FrameWorks']) && isset($_POST['Databases'])) {  //continue from here}


    $name = $_POST['name'];
    $programmingLanguages = $_POST['programmingLanguages'];
    $tools = $_POST['tools'];
    $FrameWorks = $_POST['FrameWorks'];
    $Databases = $_POST['Databases'];
    $Skills = $db->StoreUserSkills($name,$programmingLanguages,$FrameWorks,$Databases);
    if($Skills){

        $response["error"] = FALSE;
        $response['programmingLanguages'] = $Skills['programmingLanguages'];
        $response['tools'] = $Skills['tools'];
        $response['FrameWorks'] = $Skills['FrameWorks'];
        $response['Databases'] = $Skills['Databases'];
        $response['name'] = $Skills['name'];

        echo json_encode($response);
    }

}