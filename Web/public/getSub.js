
var Submission = Parse.Object.extend("Content");
var query = new Parse.Query(Submission);
query.equalTo("index", Math.floor(Math.random() * (20))); 
query.find({
  success: function(results) {
    if(results.length > 0){
	console.log(results);
	console.log(results[0].attributes.Text);
    document.getElementById("sub").innerHTML="<p>'"+results[0].attributes.Text+"'</p>";
	
	if(results[0].attributes.Picture.url()){
		document.getElementById("sub").innerHTML+="<img src=\'"+results[0].attributes.Picture.url()+"\' width=500px height=500px>";
	}
	
  }},
	error: function(error) {
		alert("Error: " + error.code + " " + error.message);
	}
	}
);
