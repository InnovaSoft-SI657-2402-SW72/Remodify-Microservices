from fastapi import FastAPI

app = FastAPI()

PUBLIC_URLS = ["/docs", "/authentication", "/openapi.json"]

@app.get("/")
def read_root():
    return {"message": "Hello World"}

@app.get("/public-urls")
def get_public_urls():
    return {"public_urls": PUBLIC_URLS}