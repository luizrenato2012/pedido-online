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
}