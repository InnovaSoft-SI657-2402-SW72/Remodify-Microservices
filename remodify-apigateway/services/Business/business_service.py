from fastapi import APIRouter, Request
import requests
from configs.url_services import MICROSERVICE_BUSINESS
from models.Business import BusinessResource

business_router =  APIRouter()

@business_router.get("/businesses/")
async def route_to_service_business(request: Request):
    url = f"{MICROSERVICE_BUSINESS}"
    response = requests.get(url, headers=request.headers)
    return response.json()

@business_router.post("/businesses/")
async def route_to_service_business(request: Request, businessResource: BusinessResource):
    url = f"{MICROSERVICE_BUSINESS}"
    response = requests.post(url, headers=request.headers, json=businessResource.dict())
    return response.json()

@business_router.get("/businesses/{id}")
async def route_to_service_business(request: Request, id: int):
    url = f"{MICROSERVICE_BUSINESS}/{id}"
    response = requests.get(url, headers=request.headers)
    return response.json()