import React, { useState } from 'react';
import { Card, CardHeader, CardContent } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from '@/components/ui/dialog';
import { Textarea } from '@/components/ui/textarea';
import { Star } from 'lucide-react';

const StarRating = ({ value, onChange, disabled }) => {
  return (
    <div className="flex gap-1">
      {[1, 2, 3, 4, 5].map((star) => (
        <button
          key={star}
          onClick={() => !disabled && onChange(star)}
          className={`focus:outline-none ${disabled ? 'cursor-default' : 'cursor-pointer'}`}
        >
          <Star
            className={`w-6 h-6 ${
              star <= value ? 'fill-yellow-400 text-yellow-400' : 'fill-none text-gray-300'
            }`}
          />
        </button>
      ))}
    </div>
  );
};

const Event = ({
  event = {
    id: '',
    name: '',
    description: '',
    category: '',
    startTime: new Date().toISOString(),
    endTime: new Date().toISOString(),
    currentAttendees: 0,
    maxAttendees: 0
  },
  isAdmin = false,
  onRSVP = () => {}
}) => {
  const [showRatingModal, setShowRatingModal] = useState(false);
  const [rating, setRating] = useState(0);
  const [recommendation, setRecommendation] = useState(0);
  const [comment, setComment] = useState('');

  const isEventPassed = new Date(event.endTime) < new Date();
  const canRSVP = !isEventPassed && event.currentAttendees < event.maxAttendees;

  const handleSubmitRating = () => {
    // Submit rating logic here
    setShowRatingModal(false);
    setRating(0);
    setRecommendation(0);
    setComment('');
  };

  return (
    <Card className="w-full max-w-2xl mx-auto mb-6">
      <CardHeader>
        <h3 className="text-2xl font-bold">{event.name || 'Untitled Event'}</h3>
      </CardHeader>
      <CardContent>
        <div className="text-gray-600 mb-4">{event.description || 'No description available'}</div>

        <div className="grid grid-cols-2 gap-4 mb-4">
          <div>
            <span className="font-semibold">Category:</span> {event.category || 'Uncategorized'}
          </div>
          <div>
            <span className="font-semibold">Availability:</span>
            {` ${event.currentAttendees || 0}/${event.maxAttendees || 0}`}
          </div>
          <div>
            <span className="font-semibold">Start:</span>
            {` ${new Date(event.startTime).toLocaleString()}`}
          </div>
          <div>
            <span className="font-semibold">End:</span>
            {` ${new Date(event.endTime).toLocaleString()}`}
          </div>
        </div>

        {canRSVP && !isAdmin && (
          <Button
            onClick={() => onRSVP(event.id)}
            variant="default"
            className="w-full"
          >
            RSVP for Event
          </Button>
        )}

        {isEventPassed && !isAdmin && (
          <Button
            onClick={() => setShowRatingModal(true)}
            variant="secondary"
            className="w-full"
          >
            Rate Event
          </Button>
        )}

        {isAdmin && (
          <div className="flex gap-4">
            <Button variant="secondary">
              Edit Event
            </Button>
            <Button variant="destructive">
              Archive Event
            </Button>
          </div>
        )}
      </CardContent>

      <Dialog open={showRatingModal} onOpenChange={setShowRatingModal}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Rate this Event</DialogTitle>
          </DialogHeader>

          <div className="space-y-4 py-4">
            <div>
              <label className="block mb-2 font-medium">Event Rating</label>
              <StarRating
                value={rating}
                onChange={setRating}
              />
            </div>

            <div>
              <label className="block mb-2 font-medium">Would you recommend this?</label>
              <StarRating
                value={recommendation}
                onChange={setRecommendation}
              />
            </div>

            <div>
              <label className="block mb-2 font-medium">Comment</label>
              <Textarea
                value={comment}
                onChange={(e) => setComment(e.target.value)}
                rows={4}
              />
            </div>
          </div>

          <DialogFooter>
            <Button
              variant="outline"
              onClick={() => setShowRatingModal(false)}
            >
              Cancel
            </Button>
            <Button
              variant="default"
              onClick={handleSubmitRating}
            >
              Submit Rating
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </Card>
  );
};

export default Event;