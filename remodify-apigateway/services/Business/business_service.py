from fastapi import APIRouter, Request, HTTPException
import requests
from configs.url_services import MICROSERVICE_BUSINESS
from models.Business import BusinessResource

business_router = APIRouter()

@business_router.get("/businesses/")
async def route_to_service_business(request: Request):
    url = f"{MICROSERVICE_BUSINESS}/api/v1/businesses"
    response = requests.get(url, headers=request.headers)
    if response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()

@business_router.post("/businesses/")
async def route_to_service_business(request: Request, businessResource: BusinessResource):
    url = f"{MICROSERVICE_BUSINESS}/api/v1/businesses"
    response = requests.post(url, headers=request.headers, json=businessResource.dict())
    if response.status_code != 201:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()

@business_router.get("/businesses/{id}")
async def route_to_service_business(request: Request, id: int):
    if id <= 0:
        raise HTTPException(status_code=400, detail="Invalid ID")
    
    url = f"{MICROSERVICE_BUSINESS}/api/v1/businesses/{id}"
    response = requests.get(url, headers=request.headers)
    if response.status_code == 404:
        raise HTTPException(status_code=404, detail="ID not found")
    elif response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    
    return response.json()