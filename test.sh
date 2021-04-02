API=https://reecipe-weekly-default-rtdb.europe-west1.firebasedatabase.app

printf "= DELETE EVERYTHING =\n"
curl -X DELETE $API/users.json
curl -X DELETE $API/recipes.json
printf "\n\n\n"

printf "= POST USERS =\n"
curl -X POST $API/users/jorge.json -d '{
	"name": "Jorge",
	"surname": "Marcos Chavez",
	"email": "jorge@reecipes.com",
	"password": "67C888AF8AD80F0232832431FB0BBB478F12740FF8B451D8D4CE0238A2D8B63A",
	"recipes": ["d8ah2ea3"]
}'
curl -X POST $API/users/manya.json -d '{
	"name": "Manya",
	"surname": "Khanna",
	"email": "manya@reecipes.com",
	"password": "BD969324DC013480BA4395DCA0B793A223684B7EC00C96831A840E9ED11FB50C",
	"recipes": ["d8ah2ea3"]
}'

curl -X GET $API/users.json
printf "\n\n\n"

printf "= POST RECIPES =\n"
curl -X POST $API/recipes/d8ah2ea3.json -d '{
	"user": "jorge",
	"date": "16:22:37 20/02/2021",
	"name": "Cheese bread",
	"description": "Bread filled with cheese, what else do you wanna know mate.",
	"time": "1 hour",
	"ingredients": {
		"warm milk": "250 ml",
		"olive oil": "50 gr",
		"dry bread yeast": "8 gr",
		"sugar": "1 tablespoon",
		"honey": "1 tablespoon",
		"salt": "1 teaspoon",
		"flour": "500 gr"
	},
	"steps": [
		"Get a big bowl and mix all the ingredients in order. First, pour the milk, then mix the yeast. Afterwards, throw the oil, sugar, honey, salt and flour in.",
		"Set your oven to preheat to 200ºC",
		"Mix and knead well, better with a kneading machine.",
		"Prepare your long mold covering it with a fine layer of oil on the inside so that the bread does not stick.",
		"Get the mix out of the bowl, stretch and wrap it into a cylinder shape that fits in your mold.",
		"Put the mix in the mold, cover it with a cloth and wait for it to grow.",
		"Cover the top part of the mix with a thin layer of oil and put the mold with the mix in the oven for about 35 minutes.",
		"Get the bread out of the mold and let it cool. If you want the crust to be soft, put the bread in a bag while it is still hot."
	]
}'

curl -X GET $API/recipes.json

