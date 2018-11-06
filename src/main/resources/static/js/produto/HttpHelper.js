class HttpHelper {

	const OK = 200;
	const DONE = 4;
	
	get( url ) {
		return new Promisse ( (resolve, reject) => {
			const xhr = new XMLHttpRequest();
			xhr.open(url);
			xhr.onreadystatechange = () => {
				if (xhr.readyState == DONE ) {
					if(xhr.status == OK) {
						// console.log('Obtendo a lista de produtos');
						resolve(JSON.parse(xhr.responseText));
						console.log('lista: '+ lista);
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