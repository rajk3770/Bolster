/**
 * Created by Tomasz Gabrysiak @ Infermedica on 08/02/2017.
 */

import html from '../../templates/helpers';
import _ from 'lodash';

const template = (context) => {
  return new Promise((resolve) => {
    context.api.getSuggestedSymptoms(context.data).then((suggestedSymptoms) => {
      if (!suggestedSymptoms.length) {
        resolve('<p><i class="fa fa-circle-o-notch fa-spin fa-fw"></i> I am thinking...</p>');
        document.getElementById('next-step').click();
      }
      resolve(html`
          <h5 class="card-title">Do you have any of the following symptoms?</h5>
          <div class="card-text">
            <form>
              ${_.take(suggestedSymptoms, 5).map(symptom => {
                return html`
                  <div class="form-group">
                    <label class="custom-control custom-checkbox mb-2 mr-sm-2 mb-sm-0">
                      <input id="${symptom.id}" type="checkbox" class="input-symptom custom-control-input">
                      <span class="custom-control-indicator"></span>
                      <span class="custom-control-description">${symptom.name}</span>
                    </label>
                  </div>
                `;
              })}
            </form>
            
          </div>
        `);
    });
  });
};

export default template;
