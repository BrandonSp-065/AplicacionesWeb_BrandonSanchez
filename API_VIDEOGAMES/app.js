const express=require   ("express");
const app=express ();

const PORT=3000;

const games=[
    { "id": 1, "Name": "Among Us", "genre": "strategy" },
    { "id": 2, "Name": "Clash Royale", "genre": "Tower Defense" },
    { "id": 3, "Name": "Minecraft", "genre": "Aventura" },
];

app.get("/games",(req, res)=>{
    res.json(games);
});

app.get("/games2",(req, res)=>{
    res.json(games);
});

app.listen(PORT ,()=> {
    console.log("App is runing on port"+PORT)
});

