const express = require('express');
const https = require("https");
const bodyParser = require('body-parser');
const cors = require('cors');
const unirest = require('unirest');

const app = express();
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());


const apiKey = "2817ad647d8c4657b526b1770ca1efc2";


app.use((req, res, next)=> {
    res.setHeader("Access-Control-Allow-Origin", "*");
    res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE");
    res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    
    next();

});


app.get("/api",(req, res, next) => {

    const url = 'https://api.currencyfreaks.com/latest?apikey=' + apiKey + '&symbols=EUR,USD,PKR,INR';

    
    https.get(url, (response) => {
        let resChuncks = "";

        response.on("data", d=> {
            resChuncks += d;
        });

        response.on("end", ()=> {
            var responseBody = JSON.parse(resChuncks);
            console.log(responseBody);
            res.send(responseBody);
            //console.log(responseBody);

            // res.write("<table border = 2px><thead><tr><th>USERID</th><th>ID</th><th>TITLE</th><th>BODY</th></tr></thead><tbody>");

            // for(i = 0; i < responseBody.length; i++){
            //     res.write("<tr><td>" + responseBody[i].userId + "</td>");
            //     res.write("<td>" + responseBody[i].id + "</td>");
            //     res.write("<td>" + responseBody[i].title + "</td>");
            //     res.write("<td>" + responseBody[i].body + "</td></tr>");
            // }

            // res.write("</tbody></table>");
     
        })

    })
}

    //     res.send(unirest('GET', 'https://api.currencyfreaks.com/latest?apikey=' + apiKey + '&symbols=EUR,USD,PKR,INR')
    //     .end(function (res) {
    //     if (res.error) throw new Error(res.error);
    //     // console.log(res.raw_body);
    //   }));
); 



const port = process.env.PORT || 6969;
app.listen(port, ()=> console.log("The server is listening at Port " + port));
