<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
?>
<html>

<button  onclick="fet()">get</button>
<script>
function fet(){
fetch("http://localhost/QuranClub/api/SURAH/asd.php").then(x => {console.log(x.text())});
}

</script>
</html>

