Parse.Cloud.define("giveSub", function(request, response) {
	
	var query = new Parse.Query("Content");
	var length = 0;
	query.ascending("index");
	query.find({
		success: function(result){
			console.log(result);
			console.log("oldLength " + length);
			length = result.length;
			console.log("length" + length);
			var queryThatReturns = new Parse.Query("Content");
			var rand = Math.random();
			console.log("random" + rand);

			var randIndex = Math.floor(rand * (length));
			console.log("index" + randIndex);
			queryThatReturns.equalTo("index", randIndex);
			queryThatReturns.find({
				success: function(result){
					console.log("ConsoleResult "+ result);
					response.success(result);
				}
			});
		}, error:function(result){
			console.log(error.message);
		}
	});
	
	// console.log("newLength " + length)
	
	// var queryThatReturns = new Parse.Query("Content");
	// var rand = Math.random();
	// console.log("random" + rand);
	
	// var randIndex = Math.floor(rand * (length-1));
	// console.log("index" + randIndex);
	// queryThatReturns.equalTo("index", randIndex);
	// queryThatReturns.find({
		// success: function(result){
			// response.success(result[0]);
		// }
	// })
	
});

Parse.Cloud.afterSave('Content', function(request) {
	
    var query = new Parse.Query("Content");
	query.ascending("createdAt");
	query.find({
		success: function(result){
			var lastRow = (result.length) - 1;
			//console.log(lastRow);
			var index = result[lastRow-1].get("index");
			//console.log(index);
			//console.log(lastRow.id);
			result[lastRow].set("index", index+1);
			result[lastRow].save();
		}
	});
});
