class HttpHelper {

	
	get(url) {
		return new Promise ( (resolve, reject) => {
			const OK = 200;
			const DONE = 4;
			const xhr = new XMLHttpRequest();
			xhr.open('GET', url);
			xhr.onreadystatechange = () => {
				if (xhr.readyState == DONE ) {
					if(xhr.status == OK) {
						// console.log('Obtendo a lista de produtos');
						resolve(JSON.parse(xhr.responseText));
					} else {
						console.log('xhr status:  '+ xhr.readyState );
						reject({status :xhr.status, error : xhr.responseText});
					}
				} 
			}
			xhr.send();
		});
	}
	
	post(url, objeto) {
		return new Promise( (resolve, reject) => {
			//200, CREATED = 201, ACCEPTED = 202 
			const STATUS_OK = [200, 201, 202];
			const DONE = 4;
			const xhr = new XMLHttpRequest();
			xhr.open('POST', url);
			xhr.setRequestHeader("Content-Type", "application/json");
			xhr.onreadystatechange = () => { 
				if (xhr.readyState == DONE ) {
					if(STATUS_OK.includes(xhr.status)) {
						resolve(JSON.parse(xhr.responseText));
					} else {
						console.log('xhr status:  '+ xhr.readyState );
						reject({status : xhr.status, error: xhr.responseText});
					}
				} 
			};
			xhr.send(JSON.stringify(objeto));
		});
	}
	
	
}