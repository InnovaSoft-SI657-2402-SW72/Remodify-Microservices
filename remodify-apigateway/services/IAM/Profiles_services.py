from fastapi import APIRouter, Request
import requests
from configs.url_services import MICROSERVICE_IAM
from models.Profiles import ProfilesResource

profiles_router = APIRouter()

@profiles_router.get("/profiles/")
async def route_to_service_profiles(request: Request):
    url = f"{MICROSERVICE_IAM}/profiles"
    response = requests.get(url, headers=request.headers)
    return response.json()

@profiles_router.post("/profiles/")
async def route_to_service_profiles(request: Request, profilesResource: ProfilesResource):
    url = f"{MICROSERVICE_IAM}/profiles"
    response = requests.post(url, headers=request.headers, json=profilesResource.dict())
    return response.json()

@profiles_router.get("/profiles/{profileId}")
async def route_to_service_profiles(request: Request, profileId: int):
    url = f"{MICROSERVICE_IAM}/profiles/{profileId}"
    response = requests.get(url, headers=request.headers)
    return response.json()