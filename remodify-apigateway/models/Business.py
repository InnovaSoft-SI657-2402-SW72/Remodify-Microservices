from pydantic import BaseModel

class BusinessResource(BaseModel):
    name: str
    description: str
    address: str
    city: str
    image: str
    expertise: str
    remodelerId: int

