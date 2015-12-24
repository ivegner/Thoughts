Parse.initialize("amWtu0dCB9xx0XJ8a4dQA4cy7XayDikxCGpdhkVb", "KbSbppFkSrNSFdzZMB7UXQbI4E0Ps8FNS9dwGMVy")

function Submit(){

	var Submission =Parse.Object.extend("Content");
	var textSubmission = new Submission();
	
	var user = document.getElementById("user").innerHTML;
	console.log(user);
	var text = (document.getElementById("SubField").value);
	console.log(text);
	
	textSubmission.set("User", user);
	textSubmission.set("Text", text);
	textSubmission.save({"User":user, "Text": text}, {success: function(){alert("ayy!")}});
	
	// .save(
	// {User: user, 
	// Text: text}, 
	// {success: 
	// error: function(){
		// alert("ah")
	// }}).then(alert("Your submission has been posted!"));

}