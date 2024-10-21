from fastapi import APIRouter, Request
import requests
from configs.url_services import MICROSERVICE_REVIEWS
from models.Reviews import ReviewsResource

reviews_router = APIRouter()

@reviews_router.get("/reviews/")
async def route_to_service_reviews(request: Request):
    url = f"{MICROSERVICE_REVIEWS}"
    response = requests.get(url, headers=request.headers)
    return response.json()

@reviews_router.post("/reviews/")
async def route_to_service_reviews(request: Request, reviewsResource: ReviewsResource):
    url = f"{MICROSERVICE_REVIEWS}"
    response = requests.post(url, headers=request.headers, json=reviewsResource.dict())
    return response.json()

@reviews_router.put("/reviews/{id}")
async def route_to_service_reviews(request: Request, id: int, reviewsResource: ReviewsResource):
    url = f"{MICROSERVICE_REVIEWS}/{id}"
    response = requests.put(url, headers=request.headers, json=reviewsResource.dict())
    return response.json()

@reviews_router.delete("/reviews/{id}")
async def route_to_service_reviews(request: Request, id: int):
    url = f"{MICROSERVICE_REVIEWS}/{id}"
    response = requests.delete(url, headers=request.headers)
    return response.json()