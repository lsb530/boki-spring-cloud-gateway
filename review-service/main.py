import uvicorn
from fastapi import FastAPI

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/hello/{name}")
async def say_hello(name: str):
    return {"message": f"Hello {name}"}


@app.get("/fast/api/reviews")
async def get_reviews():
    reviews = [
        {
            "id": 1,
            "bookId": 1,
            "name": "Park",
            "title": "amazing Harry"
        },
        {
            "id": 2,
            "bookId": 2,
            "name": "Kim",
            "title": "Slam Dunk is Legend"
        }
    ]
    return reviews


if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=6004)
