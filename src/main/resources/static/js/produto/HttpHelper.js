class HttpHelper {

	
	get( url ) {
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
						reject(xhr.responseText);
					}
				} 
			}
			xhr.send();
		});
	}
	
	post(url, objeto) {
		return new Promise(
			const OK = 200;
			const DONE = 4;
			const xhr = new XMLHttpRequest();
			xhr.open('POST', '/json-handler');
			xhr.setRequestHeader("Content-Type", "application/json");
			xhr.onreadystatechange = () => { 
				if (xhr.readyState == DONE ) {
					if(xhr.status == OK) {
						resolve(JSON.parse(xhr.responseText));
					}
				} else {
					console.log('xhr status:  '+ xhr.readyState );
					reject(xhr.responseText);
				}
			};
			
			xhr.send(JSON.stringify(objeto));
		);
	}
	
	
}