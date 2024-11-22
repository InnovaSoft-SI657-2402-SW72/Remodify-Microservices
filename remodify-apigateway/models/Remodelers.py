from pydantic import BaseModel, Field

class RemodelersResource(BaseModel):
    userId: int = Field(..., gt=0)
    description: str = Field(..., min_length=1)
    phone: str = Field(..., min_length=1)