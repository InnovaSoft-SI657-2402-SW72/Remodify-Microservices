from pydantic import BaseModel

class RemodelersResource(BaseModel):
    description: str
    phone: str
    subscription: str