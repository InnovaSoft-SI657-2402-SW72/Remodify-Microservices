from fastapi import APIRouter, Request
import requests
from configs.url_services import MICROSERVICE_REVIEWS
from models.Reviews import ReviewsResource
from models.Reviews import UpdateReviewResource


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

@reviews_router.put("/reviews/{reviewId}")
async def route_to_service_reviews(request: Request, reviewId: int, updateReviewResource: UpdateReviewResource):
    url = f"{MICROSERVICE_REVIEWS}/{reviewId}"
    response = requests.put(url, headers=request.headers, json=updateReviewResource.dict())
    return response.json()

@reviews_router.delete("/reviews/{reviewId}")
async def route_to_service_reviews(request: Request, reviewId: int):
    url = f"{MICROSERVICE_REVIEWS}/{reviewId}"
    response = requests.delete(url, headers=request.headers)
    return response.json()