from fastapi import APIRouter, Request, HTTPException
import requests
from configs.url_services import MICROSERVICE_REVIEWS
from models.Reviews import ReviewsResource, UpdateReviewResource

reviews_router = APIRouter()

@reviews_router.get("/reviews/")
async def route_to_service_reviews(request: Request):
    url = f"{MICROSERVICE_REVIEWS}"
    response = requests.get(url, headers=request.headers)
    if response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()

@reviews_router.post("/reviews/")
async def route_to_service_reviews(request: Request, reviewsResource: ReviewsResource):
    url = f"{MICROSERVICE_REVIEWS}"
    response = requests.post(url, headers=request.headers, json=reviewsResource.dict())
    if response.status_code != 201:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()

@reviews_router.put("/reviews/{reviewId}")
async def route_to_service_reviews(request: Request, reviewId: int, updateReviewResource: UpdateReviewResource):
    if reviewId <= 0:
        raise HTTPException(status_code=400, detail="Invalid ID")
    
    url = f"{MICROSERVICE_REVIEWS}/{reviewId}"
    response = requests.put(url, headers=request.headers, json=updateReviewResource.dict())
    if response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    
    return response.json()

@reviews_router.delete("/reviews/{reviewId}")
async def route_to_service_reviews(request: Request, reviewId: int):
    if reviewId <= 0:
        raise HTTPException(status_code=400, detail="Invalid ID")
    
    url = f"{MICROSERVICE_REVIEWS}/{reviewId}"
    response = requests.delete(url, headers=request.headers)
    if response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    
    return {"message": "Review deleted successfully"}