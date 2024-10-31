from pydantic import BaseModel

class ReviewsResource(BaseModel):
    contractorId: int
    projectId: int
    duration: str
    rating: int
    comment: str
    image: str


class UpdateReviewResource(BaseModel):
    duration: str
    comment: str
    image: str